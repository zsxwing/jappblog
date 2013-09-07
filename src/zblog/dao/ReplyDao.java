package zblog.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.orm.jdo.JdoCallback;
import org.springframework.orm.jdo.JdoTemplate;

import zblog.Util;
import zblog.entry.Article;
import zblog.entry.Reply;

public class ReplyDao extends JdoTemplate {

	public void reply(Article article, Reply reply) {
		reply(article.getArticleID(), reply);
	}

	public void reply(long articleID, Reply reply) {
		reply.setArticleID(articleID);
		save(reply);
	}

	public void save(Reply reply) {
		this.makePersistent(reply);
		updateReplyInCache(reply);
	}

	public void delete(long id) {
		Reply reply = get(id);
		this.deletePersistent(reply);
		deleteReplyInCache(reply);
	}

	public Reply get(long id) {
		Reply reply = (Reply) Util.getFromCache("reply_" + id);
		if (reply == null) {
			reply = (Reply) this.getObjectById(Reply.class, id);
		}
		return reply;
	}

	public void deleteReply(Article article) {
		Collection<Reply> replies = queryReply(article);
		this.deletePersistentAll(replies);
		for (Reply reply : replies) {
			deleteReplyInCache(reply);
		}
	}

	private void updateReplyInCache(Reply reply) {
		Util.putToCache("reply_" + reply.getReplyID(), reply);
		Util.deleteFromCache("article_reply_" + reply.getArticleID());
		Util.deleteFromCache("latest_replies");
	}

	private void deleteReplyInCache(Reply reply) {
		Util.deleteFromCache("reply_" + reply.getReplyID());
		Util.deleteFromCache("article_reply_" + reply.getArticleID());
		Util.deleteFromCache("latest_replies");
	}

	@SuppressWarnings("unchecked")
	public Collection<Reply> queryReply(Article article) {
		Collection<Reply> replies = (Collection<Reply>) Util
				.getFromCache("article_reply_" + article.getArticleID());
		if (replies == null) {
			replies = (Collection<Reply>) this.find(Reply.class,
					"articleID == articleIDParam", "Long articleIDParam",
					new Object[] { article.getArticleID() });
			Util.putToCache("article_reply_" + article.getArticleID(),
					new ArrayList<Reply>(replies));
		}
		return replies;
	}

	@SuppressWarnings("unchecked")
	public Collection<Reply> getLatestReply() {
		Collection<Reply> replies = (Collection<Reply>) Util
				.getFromCache("latest_replies");
		if (replies == null) {
			replies = (Collection<Reply>) execute(new JdoCallback() {
				public Object doInJdo(PersistenceManager pm)
						throws JDOException {
					Query query = pm.newQuery(Reply.class);
					prepareQuery(query);
					query.setOrdering("date desc");
					query.setRange(0, 5);
					return query.execute();
				}
			}, true);
			Util.putToCache("latest_replies", new ArrayList<Reply>(replies));
		}
		return replies;
	}
}

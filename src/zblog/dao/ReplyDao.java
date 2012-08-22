package zblog.dao;

import java.util.Collection;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.orm.jdo.JdoCallback;
import org.springframework.orm.jdo.JdoTemplate;

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
	}

	public void delete(long id) {
		this.deletePersistent(get(id));
	}

	public Reply get(long id) {
		return (Reply) this.getObjectById(Reply.class, id);
	}

	public void deleteReply(Article article) {
		this.deletePersistentAll(queryReply(article));
	}

	@SuppressWarnings("unchecked")
	public Collection<Reply> queryReply(Article article) {
		try {
			return (Collection<Reply>) this.find(Reply.class,
					"articleID == articleIDParam", "Long articleIDParam",
					new Object[] { article.getArticleID() });
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<Reply> getLatestReply() {
		try {
			return (Collection<Reply>) execute(new JdoCallback() {
				public Object doInJdo(PersistenceManager pm)
						throws JDOException {
					Query query = pm.newQuery(Reply.class);
					prepareQuery(query);
					query.setOrdering("date desc");
					query.setRange(0, 5);
					return query.execute();
				}
			}, true);
		} catch (Exception e) {
		}
		return null;
	}
}

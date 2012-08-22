package zblog.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.store.appengine.query.JDOCursorHelper;
import org.springframework.orm.jdo.JdoCallback;
import org.springframework.orm.jdo.JdoTemplate;

import zblog.entry.Article;

import com.google.appengine.api.datastore.Cursor;

public class ArticleDao extends JdoTemplate {

	public void save(Article article) {
		this.makePersistent(article);
	}

	public Article get(long id) {
		return (Article) this.getObjectById(Article.class, id);
	}

	public void delete(long id) {
		this.deletePersistent(get(id));
	}
	
	public void delete(Article article) {
		this.deletePersistent(article);
	}

	@SuppressWarnings("unchecked")
	public Collection<Article> list(final String catelog) {
		return (Collection<Article>) this.find(Article.class,
				"catelog == catelogParam", "String catelogParam",
				new String[] { catelog }, "date desc");
	}

	@SuppressWarnings("unchecked")
	public Collection<Article> listLatest() {
		return (Collection<Article>) execute(new JdoCallback() {
			public Object doInJdo(PersistenceManager pm) throws JDOException {
				Query query = pm.newQuery(Article.class);
				prepareQuery(query);
				query.setOrdering("date desc");
				query.setRange(0, 10);
				return query.execute();
			}
		}, true);
	}

	@SuppressWarnings("unchecked")
	public Collection<Article> list(final String catelog,
			final String cursorString, final int limit, StringBuilder nextCursor) {

		List<Article> results = (List<Article>) execute(new JdoCallback() {
			public Object doInJdo(PersistenceManager pm) throws JDOException {
				Query query = null;
				Object[] params = null;

				if (catelog != null) {
					query = pm.newQuery(Article.class,
							"catelog == catelogParam ");
					query.declareParameters("String catelogParam");
					params = new Object[] { catelog };
				} else {
					query = pm.newQuery(Article.class);
					params = new Object[] {};
				}

				prepareQuery(query);
				query.setRange(0, limit);
				query.setOrdering("date desc");

				if (cursorString != null) {
					Cursor cursor = Cursor.fromWebSafeString(cursorString);
					Map<String, Object> extensionMap = new HashMap<String, Object>();
					extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
					query.setExtensions(extensionMap);
				}

				return query.executeWithArray(params);
			}
		}, true);

		if (results.size() == limit) {
			Cursor cursor = JDOCursorHelper.getCursor(results);
			nextCursor.append(cursor.toWebSafeString());
		}
		return results;
	}
}

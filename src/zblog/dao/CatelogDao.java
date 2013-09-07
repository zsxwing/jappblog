package zblog.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.orm.jdo.JdoTemplate;

import zblog.Util;
import zblog.entry.Catelog;

public class CatelogDao extends JdoTemplate {

	private ArticleDao articleDao;

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@SuppressWarnings("unchecked")
	public Collection<Catelog> list() {
		Collection<Catelog> catelogs = (Collection<Catelog>) Util
				.getFromCache("catelogs");
		if (catelogs == null) {
			catelogs = this.find(Catelog.class);
			Util.putToCache("catelogs", new ArrayList<Catelog>(catelogs));
		}
		return catelogs;
	}

	public void save(Catelog catelog) {
		this.makePersistent(catelog);
		Util.deleteFromCache("catelogs");
	}

	public void save(Collection<Catelog> catelogs) {
		this.makePersistentAll(catelogs);
		Util.deleteFromCache("catelogs");
	}

	@SuppressWarnings("rawtypes")
	public Catelog get(String name) {
		try {
			Collection c = this.find(Catelog.class, "name == nameParam",
					"String nameParam", new String[] { name });
			if (c != null && c.size() >= 1) {
				return (Catelog) c.iterator().next();
			}
		} catch (Exception e) {
		}
		return null;
	}

	public void delete(String name) {
		Catelog catelog = get(name);
		if (catelog != null) {
			this.deletePersistentAll(articleDao.list(name));
			this.deletePersistent(catelog);
			Util.deleteFromCache("catelogs");
		}
	}
}

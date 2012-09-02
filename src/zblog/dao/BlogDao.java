package zblog.dao;

import java.util.Collection;

import org.springframework.orm.jdo.JdoTemplate;

import zblog.entry.Blog;

public class BlogDao extends JdoTemplate {

	public void save(Blog blog) {
		this.makePersistent(blog);
	}

	@SuppressWarnings("rawtypes")
	public Blog get() {
		Collection blog = this.find(Blog.class);
		if (blog != null && blog.size() >= 1) {
			// 永远只用第一个，防止多次的save导致多个的冲突
			return (Blog) blog.iterator().next();
		}
		return null;
	}

}

package zblog.dao;

import java.util.Collection;

import org.springframework.orm.jdo.JdoTemplate;

import zblog.Util;
import zblog.entry.Blog;

public class BlogDao extends JdoTemplate {

	public void save(Blog blog) {
		this.makePersistent(blog);
		Util.putToCache("blog", blog);
	}

	public Blog get() {
		Blog cachedBlog = (Blog) Util.getFromCache("blog");
		if (cachedBlog != null) {
			return cachedBlog;
		}
		Collection<?> blog = this.find(Blog.class);
		if (blog != null && blog.size() >= 1) {
			// 永远只用第一个，防止多次的save导致多个的冲突
			Blog rawBlog = (Blog) blog.iterator().next();
			Util.putToCache("blog", rawBlog);
			return rawBlog;
		}
		return null;
	}

}

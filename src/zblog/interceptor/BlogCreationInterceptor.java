package zblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import zblog.dao.BlogDao;

/**
 * 当访问任意页面时，检查博客是否已经正确地被配置。如果还未配置，转到博客配置的页面。
 * 
 * @author wing
 * 
 */
public class BlogCreationInterceptor extends HandlerInterceptorAdapter {

	private BlogDao blogDao;

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getRequestURI().startsWith("/admin/setup.html")) {
			return true;
		}

		if (blogDao.get() == null) {
			response.sendRedirect("/admin/setup.html");
			return false;
		}
		return true;
	}
}

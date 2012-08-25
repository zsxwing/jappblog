package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.Dao;
import zblog.entry.Article;
import zblog.entry.Catelog;

public class DeleteController extends AbstractController {
	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 删除文章，回到首页
		if (request.getParameter("id") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				Article article = dao.getArticleDao().get(id);

				Catelog catelog = dao.getCatelogDao().get(article.getCatelog());
				catelog.setCount(catelog.getCount() - 1);
				dao.getCatelogDao().save(catelog);

				dao.getArticleDao().delete(article);

				response.sendRedirect("/");
				return null;
			} catch (NumberFormatException e) {
			}
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;

	}
}

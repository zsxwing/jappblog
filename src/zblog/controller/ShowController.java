package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.Dao;
import zblog.entry.Article;
import zblog.viewer.GeneralModelAndView;

public class ShowController extends AbstractController {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getParameter("id") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				Article article = dao.getArticleDao().get(id);
				if (article != null) {
					ModelAndView mav = new GeneralModelAndView(dao, request,
							"show", article.getTitle());
					mav.addObject("article", article);
					mav.addObject("show", true);
					mav.addObject("replies",
							dao.getReplyDao().queryReply(article));
					return mav;
				}
			} catch (NumberFormatException e) {
			}
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;

	}
}

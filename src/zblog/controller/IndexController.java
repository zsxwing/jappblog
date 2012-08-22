package zblog.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import zblog.dao.Dao;
import zblog.entry.Article;
import zblog.viewer.GeneralModelAndView;

public class IndexController extends AbstractCommandController {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public IndexController() {
		this.setCommandClass(Query.class);
		this.setCommandName("query");
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Query query = (Query) command;
		ModelAndView mav = new GeneralModelAndView(dao, request, "index");
		StringBuilder cursor = new StringBuilder();
		Collection<Article> articles = dao.getArticleDao().list(
				query.getCatelog(), query.getCursor(), 10, cursor);

		if (cursor.length() != 0) {
			String moreLink = "/?cursor=" + cursor.toString();
			if (query.getCatelog() != null) {
				moreLink += "&catelog=" + query.getCatelog();
			}
			mav.addObject("morelink", moreLink);
		}

		mav.addObject("articles", articles);
		return mav;
	}

	public static class Query {
		private String catelog;
		private String cursor;

		public String getCursor() {
			return cursor;
		}

		public void setCursor(String cursor) {
			this.cursor = cursor;
		}

		public String getCatelog() {
			return catelog;
		}

		public void setCatelog(String catelog) {
			this.catelog = catelog;
		}

	}
}

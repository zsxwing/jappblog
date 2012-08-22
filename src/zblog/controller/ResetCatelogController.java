package zblog.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.Dao;
import zblog.entry.Catelog;

public class ResetCatelogController extends AbstractController {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Collection<Catelog> catelogs = dao.getCatelogDao().list();
		for (Catelog catelog : catelogs) {
			int count = dao.getArticleDao().list(catelog.getName()).size();
			catelog.setCount(count);
		}
		dao.getCatelogDao().save(catelogs);
		response.sendRedirect("/");
		return null;
	}
}

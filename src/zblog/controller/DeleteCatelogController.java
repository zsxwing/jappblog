package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.CatelogDao;

public class DeleteCatelogController extends AbstractController {
	private CatelogDao catelogDao;

	public void setCatelogDao(CatelogDao catelogDao) {
		this.catelogDao = catelogDao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String catelog = request.getParameter("catelog");
		if (catelog != null) {
			catelogDao.delete(catelog);
			response.sendRedirect("/");
			return null;
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
}

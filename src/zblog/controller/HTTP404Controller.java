package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.Dao;
import zblog.viewer.GeneralModelAndView;

public class HTTP404Controller extends AbstractController {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new GeneralModelAndView(dao, request, "404");
		return mav;
	}

}

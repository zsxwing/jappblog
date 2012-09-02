package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jenml2html.EvernoteClient;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.Dao;
import zblog.viewer.GeneralModelAndView;

public class EvernoteNoteListController extends AbstractController {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String evernoteDeveloperToken = dao.getBlogDao().get()
				.getEvernoteDeveloperToken();
		ModelAndView mav = new GeneralModelAndView(dao, request, "evernote");
		mav.addObject("notes",
				new EvernoteClient(evernoteDeveloperToken).listNotes());
		return mav;
	}

}
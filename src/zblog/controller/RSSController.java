package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.viewer.RSSView;

public class RSSController extends AbstractController {

	private RSSView rssView;

	public void setRssView(RSSView rssView) {
		this.rssView = rssView;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView(rssView);
	}

}

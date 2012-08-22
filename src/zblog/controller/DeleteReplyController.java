package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.Util;
import zblog.dao.ReplyDao;

public class DeleteReplyController extends AbstractController {

	private ReplyDao replyDao;

	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long articleID = Util.id("articleID", request);
		long replyID = Util.id("replyID", request);
		if (articleID < 0 || replyID < 0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		replyDao.delete(replyID);
		response.sendRedirect("/show.html?id=" + articleID);
		return null;
	}
}

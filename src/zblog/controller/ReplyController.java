package zblog.controller;

import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import zblog.Util;
import zblog.dao.Dao;
import zblog.entry.Reply;
import zblog.viewer.TextView;

public class ReplyController extends AbstractCommandController {

	private static final Logger logger = Logger.getLogger(ReplyController.class
			.getName());

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public ReplyController() {
		this.setCommandClass(Reply.class);
		this.setCommandName("reply");
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		return new ModelAndView(new TextView(solve(request, response, command)));
	}

	private String solve(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {

		Reply reply = (Reply) command;

		if (StringUtils.isEmpty(reply.getAuthor())) {
			return "需要填写昵称";
		}

		if (StringUtils.isEmpty(reply.getContent())) {
			return "需要填写内容";
		}

		if (reply.getContent().length() > 200) {
			return "内容不能超过200字";
		}

		String remoteAddr = request.getRemoteAddr();
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha
				.setPrivateKey(dao.getBlogDao().get().getRecaptchaPrivateKey());

		String challenge = request.getParameter("recaptcha_challenge_field");
		String uresponse = request.getParameter("recaptcha_response_field");
		// null

		if (StringUtils.isEmpty(challenge) || StringUtils.isEmpty(uresponse)) {
			return "验证码不能为空";
		}

		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr,
				challenge, uresponse);

		if (reCaptchaResponse.isValid()) {

			reply.setDate(new Date(System.currentTimeMillis()));
			reply.setIp(Util.getRealIp(request));

			dao.getReplyDao().save(reply);
			return "success";
		} else {
			logger.warning(reCaptchaResponse.getErrorMessage());
			return "验证码错误";
		}

	}

}

package zblog.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import freemarker.log.Logger;

public class JAppHandlerExceptionResolver implements HandlerExceptionResolver {

	private Logger logger = Logger.getLogger(JAppHandlerExceptionResolver.class
			.getName());

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		logger.error(request.getRequestURI(), ex);

		try {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (IOException e) {
			logger.error(request.getRequestURI(), e);
		}
		return null;
	}

}

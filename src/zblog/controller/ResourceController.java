package zblog.controller;

import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.ResourceDao;
import zblog.entry.Resource;

import com.google.appengine.api.datastore.Blob;

public class ResourceController extends AbstractController {
	private ResourceDao resourceDao;

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getHeader("If-Modified-Since") != null) {
			response.setStatus(304);
			return null;
		}

		if (request.getParameter("id") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				Resource resource = null;

				resource = resourceDao.get(id);
				if (resource != null) {
					String contentType = resource.getContentType();

					response.setContentType(contentType);
					if (resource.getDate() == null) {
						resource.setDate(new Date(System.currentTimeMillis()));
						resourceDao.save(resource);
					}
					Date date = resource.getDate();
					response.setHeader("Last-Modified", date.toGMTString());
					date.setTime(date.getTime() + 1000L * MAX_AGE);
					response.setHeader("Expires", date.toGMTString());
					response.setHeader("Cache-Control", "max-age=" + MAX_AGE);
					Blob blob = resource.getContent();

					OutputStream out = response.getOutputStream();
					out.write(blob.getBytes());
					out.close();
					return null;
				}

			} catch (NumberFormatException e) {
			}
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	private static final long MAX_AGE = 3600L * 24 * 365;
}

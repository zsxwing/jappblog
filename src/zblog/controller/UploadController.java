package zblog.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.ResourceDao;
import zblog.entry.Resource;

import com.google.appengine.api.datastore.Blob;

public class UploadController extends AbstractController {

	private static final int DEFAULT_BUFFER_SIZE = 8192;

	private ResourceDao resourceDao;

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		ServletFileUpload upload = new ServletFileUpload();

		FileItemIterator iter;
		try {
			iter = upload.getItemIterator(request);

			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					// skip
				} else {
					response.setContentType("text/html; charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					PrintWriter out = response.getWriter();

					long id = save(item.getName(), item.getContentType(),
							stream);

					String callback = request.getParameter("CKEditorFuncNum");

					if (callback == null) {
						response.sendError(HttpServletResponse.SC_NOT_FOUND);
						return null;
					}

					String fileUrl = "/resource.html?id=" + id;

					out.println("<script type=\"text/javascript\">");
					out.println("window.parent.CKEDITOR.tools.callFunction("
							+ callback + ",'" + fileUrl + "',''" + ")");
					out.println("</script>");
					out.close();
				}
			}
		} catch (FileUploadException e) {
			throw new IOException(e);
		}

		return null;
	}

	private long save(String fileName, String contentType, InputStream stream)
			throws IOException {
		Resource resource = new Resource();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

		int len;
		while ((len = stream.read(bytes)) > 0) {
			baos.write(bytes, 0, len);
		}

		Blob blob = new Blob(baos.toByteArray());

		resource.setContentType(contentType);
		resource.setContent(blob);
		resource.setFileName(fileName);
		resource.setDate(new Date(System.currentTimeMillis()));
		resourceDao.save(resource);
		return resource.getKey().getId();
	}
}

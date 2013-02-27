package zblog.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jenml2html.ENMLToHTMLConverter;
import jenml2html.EvernoteClient;
import jenml2html.ImageConverter;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import zblog.dao.Dao;
import zblog.entry.Article;
import zblog.entry.Catelog;
import zblog.entry.Resource;

import com.evernote.edam.type.Note;
import com.google.appengine.api.datastore.Blob;

public class PostEvernoteNoteController extends AbstractCommandController {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public PostEvernoteNoteController() {
		this.setCommandClass(EvernoteNote.class);
		this.setCommandName("post_evernote_note");
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		solve(request, response, command);
		return null;
	}

	public static class EvernoteNote {
		private String guid;

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}
	}

	@SuppressWarnings("unchecked")
	private void solve(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {

		EvernoteNote note = (EvernoteNote) command;

		JSONObject object = new JSONObject();

		if (StringUtils.isEmpty(note.getGuid())) {
			object.put("status", "fail");
			object.put("msg", "guid不能为空");
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		try {
			String evernoteDeveloperToken = dao.getBlogDao().get()
					.getEvernoteDeveloperToken();
			EvernoteClient client = new EvernoteClient(evernoteDeveloperToken);
			ENMLToHTMLConverter enml2html = new ENMLToHTMLConverter(
					new ImageSaver());

			Note evernoteNote = client.getNote(note.getGuid());

			Article article = new Article();

			article.setContent(enml2html.convert(evernoteNote));
			article.setTitle(evernoteNote.getTitle());
			article.setAllowReply(true);
			article.setCatelog("未分类");

			article.setAuthor(dao.getUserService().getCurrentUser());
			article.setDate(new Date(System.currentTimeMillis()));

			dao.getArticleDao().save(article);

			Catelog newCatelog = dao.getCatelogDao().get(article.getCatelog());
			if (newCatelog == null) {
				Catelog catelog = new Catelog();
				catelog.setName(article.getCatelog());
				catelog.setCount(1);
				dao.getCatelogDao().save(catelog);
			} else {
				newCatelog.setCount(newCatelog.getCount() + 1);
				dao.getCatelogDao().save(newCatelog);
			}

			object.put("status", "success");
			object.put("id", article.getArticleID());
			response.sendRedirect("/show.html?id=" + article.getArticleID());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			object.put("status", "fail");
			object.put("msg", e.getMessage());
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

	}

	public class ImageSaver extends ImageConverter {

		@Override
		protected String upload(com.evernote.edam.type.Resource resource) {
			Resource newResource = new Resource();

			Blob blob = new Blob(resource.getData().getBody());

			newResource.setContentType(resource.getMime());
			newResource.setContent(blob);
			newResource.setFileName(resource.getAttributes().getFileName());
			newResource.setDate(new Date(resource.getAttributes()
					.getTimestamp()));
			dao.getResourceDao().save(newResource);
			return "/resource.html?id=" + newResource.getKey().getId();
		}

	}

}
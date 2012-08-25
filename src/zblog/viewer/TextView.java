package zblog.viewer;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class TextView extends AbstractView {

	private String text;

	public TextView(String text) {
		this.text = text;
	}

	@Override
	protected void renderMergedOutputModel(
			@SuppressWarnings("rawtypes") Map model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(text);
		writer.close();
	}

}

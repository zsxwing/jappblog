package zblog.viewer;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import zblog.dao.Dao;
import zblog.entry.Article;
import zblog.entry.Blog;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

public class RSSView extends AbstractView {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("application/xhtml+xml");
		response.setCharacterEncoding("UTF-8");

		Blog blog = dao.getBlogDao().get();

		SyndFeed feed = createFeed(blog);

		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		for (Article article : dao.getArticleDao().listLatest()) {
			entries.add(createEntry(blog, article));
		}
		feed.setEntries(entries);

		SyndFeedOutput output = new SyndFeedOutput();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				response.getOutputStream(), "UTF-8"));
		output.output(feed, writer);
	}

	private SyndEntry createEntry(Blog blog, Article article) {
		SyndEntry entry = new SyndEntryImpl();
		entry.setTitle(article.getTitle());
		entry.setPublishedDate(article.getCreateDate());
		entry.setLink("http://" + blog.getHost() + "/show.html?id="
				+ article.getKey().getId());

		entry.setAuthor(article.getAuthor().getNickname());

		SyndContent content = new SyndContentImpl();
		content.setType("text/html");
		content.setValue(article.getContent());
		entry.setDescription(content);

		SyndCategory category = new SyndCategoryImpl();
		category.setName(article.getCatelog());

		List<SyndCategory> categories = new ArrayList<SyndCategory>();
		categories.add(category);

		entry.setCategories(categories);

		return entry;
	}

	private SyndFeed createFeed(Blog blog) {
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setEncoding("UTF-8");
		feed.setTitle(blog.getName());
		feed.setDescription(blog.getDescription());
		feed.setLink("http://" + blog.getHost());
		feed.setLanguage("zh-cn");
		feed.setAuthor(blog.getAuthor().getNickname());
		return feed;
	}
}

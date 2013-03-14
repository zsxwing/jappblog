package zblog.entry;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;

@PersistenceCapable(detachable = "true")
public class Article {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Text content;

	@Persistent
	private String title;

	@Persistent
	private Date date;

	@Persistent
	private Date createDate;

	@Persistent
	private User author;

	@Persistent
	private Boolean allowReply;

	@Persistent
	private String catelog;

	@Persistent
	private Boolean displayMath;

	public boolean isDisplayMath() {
		return displayMath == null ? false : displayMath;
	}

	public void setDisplayMath(boolean displayMath) {
		this.displayMath = displayMath;
	}

	public String getCatelog() {
		return catelog;
	}

	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}

	public String getEncodedCatelog() {
		try {
			return URLEncoder.encode(catelog, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public long getArticleID() {
		return key.getId();
	}

	public Boolean getAllowReply() {
		return allowReply;
	}

	public void setAllowReply(Boolean allowReply) {
		this.allowReply = allowReply;
	}

	public User getAuthor() {
		return author;
	}

	public String getAuthorName() {
		return author.getNickname();
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if (createDate == null) {
			createDate = date;
		}
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Key getKey() {
		return key;
	}

	public String getContent() {
		return content == null ? null : content.getValue();
	}

	public void setContent(String content) {
		this.content = new Text(content);
	}

	public Date getCreateDate() {
		return createDate == null ? date : createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSummary() {
		String content = getContent().replaceAll("\\<(.*?)\\>", "");
		int endIndex = content.length();
		if (endIndex > 200) {
			endIndex = 200;
		}
		String summary = content.substring(0, endIndex);
		return summary;
	}

}

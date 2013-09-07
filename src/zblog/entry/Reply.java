package zblog.entry;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import zblog.Util;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class Reply implements Serializable {
	
	private static final long serialVersionUID = 6211347306992616106L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String content;

	@Persistent
	private Date date;

	@Persistent
	private String author;

	@Persistent
	private String ip;

	@Persistent
	private Long articleID;

	public Long getArticleID() {
		return articleID;
	}

	public void setArticleID(Long articleID) {
		this.articleID = articleID;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAuthor() {
		return author;
	}

	public String getAuthorName() {
		if ("匿名".equals(author)) {
			return Util.encodeIp(ip);
		}
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Key getKey() {
		return key;
	}

	public long getReplyID() {
		return key.getId();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSummaryReply() {
		int end = 10;
		if (this.content.length() < end) {
			return this.content;
		}
		return this.content.substring(0, end);
	}

}

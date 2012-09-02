package zblog.entry;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;

@PersistenceCapable(detachable = "true")
public class Blog {

	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private String description;

	@Persistent
	private String host;

	@Persistent
	private User author;

	@Persistent
	private Text trackSnippet;

	public String getTrackSnippet() {
		return trackSnippet == null ? "" : trackSnippet.getValue();
	}

	public void setTrackSnippet(String trackSnippet) {
		this.trackSnippet = new Text(trackSnippet);
	}

	@SuppressWarnings("unused")
	@Persistent
	private String theme;// will support in future

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecaptchaPublicKey() {
		return recaptchaPublicKey;
	}

	public void setRecaptchaPublicKey(String recaptchaPublicKey) {
		this.recaptchaPublicKey = recaptchaPublicKey;
	}

	public String getRecaptchaPrivateKey() {
		return recaptchaPrivateKey;
	}

	public void setRecaptchaPrivateKey(String recaptchaPrivateKey) {
		this.recaptchaPrivateKey = recaptchaPrivateKey;
	}

	@Persistent
	private String recaptchaPublicKey;

	@Persistent
	private String recaptchaPrivateKey;

	@Persistent
	private String evernoteDeveloperToken;

	public String getEvernoteDeveloperToken() {
		return evernoteDeveloperToken == null ? "" : evernoteDeveloperToken;
	}

	public void setEvernoteDeveloperToken(String evernoteDeveloperToken) {
		this.evernoteDeveloperToken = evernoteDeveloperToken;
	}

}

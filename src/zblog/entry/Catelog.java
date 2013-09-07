package zblog.entry;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class Catelog implements Serializable {

	private static final long serialVersionUID = -843243637563880971L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private String count;

	public int getCount() {
		if (count == null) {
			return 0;
		}
		return Integer.parseInt(count);
	}

	public void setCount(int count) {
		this.count = Integer.toString(count);
	}

	public String getName() {
		return name;
	}

	public String getEncodedName() {
		try {
			return URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public Key getKey() {
		return key;
	}

}

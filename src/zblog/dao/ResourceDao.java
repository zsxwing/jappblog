package zblog.dao;

import org.springframework.orm.jdo.JdoTemplate;

import zblog.entry.Resource;

public class ResourceDao extends JdoTemplate {

	public void save(Resource resource) {
		this.makePersistent(resource);
	}

	public Resource get(long id) {
		try {
			return (Resource) this.getObjectById(Resource.class, id);
		} catch (Exception e) {
			return null;
		}
	}
}

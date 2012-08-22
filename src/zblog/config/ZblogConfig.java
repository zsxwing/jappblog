package zblog.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ZblogConfig {

	private static Logger logger = Logger
			.getLogger(ZblogConfig.class.getName());

	private static Configuration instance;

	static {
		try {
			instance = new PropertiesConfiguration(ZblogConfig.class
					.getResource("config.properties"));
		} catch (ConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public static Configuration getInstance() {
		return instance;
	}
}

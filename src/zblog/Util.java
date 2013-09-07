package zblog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Util {

	public static String getRequestURL(HttpServletRequest req) {
		String thisURL = req.getRequestURI();
		boolean first = true;
		for (Object key : req.getParameterMap().keySet()) {
			if (first) {
				thisURL += "?";
				first = false;
				thisURL += (key + "=" + req.getParameter((String) key));
			} else {
				thisURL += ("&" + key + "=" + req.getParameter((String) key));
			}
		}
		return thisURL;
	}

	public static long id(String param, HttpServletRequest req) {
		String idString = req.getParameter(param);
		long id = -1;
		if (idString != null) {
			try {
				id = Long.parseLong(idString);
			} catch (NumberFormatException e) {
			}
		}
		// 出现错误的话id=-1
		return id;
	}

	public static boolean isUserAdmin(UserService userService) {
		return userService.isUserLoggedIn() && userService.isUserAdmin();
	}

	public static boolean isUserLoggedIn() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.isUserLoggedIn();
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

	public static String formatDate(Date date) {
		return format.format(date);
	}

	public static String encodeIp(String ip) {
		String[] splits = ip.split("\\.");
		if (splits.length >= 2) {
			return splits[0] + "." + splits[1] + ".*.*";
		} else {
			return "*.*.*.*";
		}
	}

	public static String getRealIp(HttpServletRequest req) {
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		return ip;
	}

	public static void putToCache(Object key, Object object) {
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		cache.setErrorHandler(ErrorHandlers
				.getConsistentLogAndContinue(Level.INFO));
		cache.put(key, object);
	}

	public static void deleteFromCache(String key) {
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		cache.setErrorHandler(ErrorHandlers
				.getConsistentLogAndContinue(Level.INFO));
		cache.delete(key);
	}

	public static Object getFromCache(String key) {
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		cache.setErrorHandler(ErrorHandlers
				.getConsistentLogAndContinue(Level.INFO));
		return cache.get(key);
	}
}

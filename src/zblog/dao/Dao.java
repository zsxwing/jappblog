package zblog.dao;

import com.google.appengine.api.users.UserService;

public class Dao {

	public BlogDao getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public CatelogDao getCatelogDao() {
		return catelogDao;
	}

	public void setCatelogDao(CatelogDao catelogDao) {
		this.catelogDao = catelogDao;
	}

	public ReplyDao getReplyDao() {
		return replyDao;
	}

	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	private BlogDao blogDao;
	private ArticleDao articleDao;
	private CatelogDao catelogDao;
	private ReplyDao replyDao;
	private UserService userService;
	
	private ResourceDao resourceDao;

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

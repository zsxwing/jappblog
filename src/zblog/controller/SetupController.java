package zblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import zblog.config.ZblogConfig;
import zblog.dao.BlogDao;
import zblog.entry.Blog;

import com.google.appengine.api.users.UserService;

public class SetupController extends SimpleFormController {

	private BlogDao blogDao;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public SetupController() {
		this.setCommandClass(Blog.class);
		this.setCommandName("blog");
		this.setValidator(new Validator() {

			@Override
			public void validate(Object target, Errors errors) {
				Blog blog = (Blog) target;
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
						"required.name", "需要填写博客名称");

				if (blog.getName().length() > ZblogConfig.getInstance().getInt(
						"blog.name.maxlength")) {
					errors.rejectValue(
							"name",
							"invalid.name",
							"博客名称不能超过"
									+ ZblogConfig.getInstance().getInt(
											"blog.name.maxlength") + "字节");
				}

				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"description", "required.description", "需要填写博客简介");

				if (blog.getDescription().length() > ZblogConfig.getInstance()
						.getInt("blog.desc.maxlength")) {
					errors.rejectValue(
							"description",
							"invalid.desc",
							"博客简介不能超过"
									+ ZblogConfig.getInstance().getInt(
											"blog.desc.maxlength") + "字节");
				}

				if (!blog.getHost().matches("[^.]+(\\.[^.]+)*")) {
					errors.rejectValue("host", "invalid.host", "域名格式有误");
				}

				if (blog.getHost().length() > ZblogConfig.getInstance().getInt(
						"blog.host.maxlength")) {
					errors.rejectValue(
							"host",
							"invalid.host",
							"域名不能超过"
									+ ZblogConfig.getInstance().getInt(
											"blog.host.maxlength") + "字节");
				}
			}

			@SuppressWarnings("rawtypes")
			@Override
			public boolean supports(Class clazz) {
				return clazz.equals(Blog.class);
			}
		});
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Blog blog = blogDao.get();
		if (blog != null) {
			return blog;
		}
		return super.formBackingObject(request);
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Blog blog = (Blog) command;
		blog.setAuthor(userService.getCurrentUser());
		blogDao.save(blog);
		response.sendRedirect("/index.html");
		return null;
	}

}

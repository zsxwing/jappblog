package zblog.viewer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import zblog.Util;
import zblog.dao.Dao;

public class GeneralModelAndView extends ModelAndView {

    public GeneralModelAndView(Dao dao, HttpServletRequest request,
            String viewName) {
        this(dao, request, viewName, dao.getBlogDao().get().getName());
    }

    public GeneralModelAndView(Dao dao, HttpServletRequest request,
            String viewName, String title) {
        super(viewName);
        this.addObject("blog", dao.getBlogDao().get());
        this.addObject("catelogs", dao.getCatelogDao().list());
        String thisURL = Util.getRequestURL(request);
        if (dao.getUserService().isUserLoggedIn()) {
            this.addObject("logout",
                    dao.getUserService().createLogoutURL(thisURL));
            if (dao.getUserService().isUserAdmin()) {
                this.addObject("admin", true);
            }
        } else {

            this.addObject("login", dao.getUserService()
                    .createLoginURL(thisURL));
        }

        if (Util.isUserLoggedIn()) {
            this.addObject("userName", dao.getUserService().getCurrentUser()
                    .getNickname());
        } else {
            this.addObject("userName", "匿名");
        }
        this.addObject("latestReplies", dao.getReplyDao().getLatestReply());
        this.addObject("title", title);
    }

}

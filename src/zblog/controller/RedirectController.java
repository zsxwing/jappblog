package zblog.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import zblog.dao.Dao;

public class RedirectController extends AbstractController {
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (request.getParameter("url") != null) {
            try {
                URL url = new URL(URLDecoder.decode(
                        request.getParameter("url"), "UTF-8"));
                if (url.getHost().equals(dao.getBlogDao().get().getHost())) {
                    response.sendRedirect(url.toString());
                }
                return null;
            } catch (NumberFormatException e) {
            } catch (MalformedURLException e) {
            }
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }
}

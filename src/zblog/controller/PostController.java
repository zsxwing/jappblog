package zblog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import zblog.dao.Dao;
import zblog.entry.Article;
import zblog.entry.Catelog;

public class PostController extends SimpleFormController {

    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public PostController() {
        this.setCommandClass(Article.class);
        this.setCommandName("article");
        this.setValidator(new Validator() {

            @Override
            public void validate(Object target, Errors errors) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
                        "required.title", "标题不能为空");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content",
                        "required.content", "文章内容不能为空");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "catelog",
                        "required.catelog", "类别不能为空");
            }

            @SuppressWarnings("rawtypes")
            @Override
            public boolean supports(Class clazz) {
                return clazz.equals(Article.class);
            }
        });
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {
        if (request.getParameter("id") != null) {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                Article article = dao.getArticleDao().get(id);
                if (article != null) {
                    Article copyArticle = new Article();
                    BeanUtils.copyProperties(article, copyArticle);
                    Pattern pattern = Pattern.compile("<pre(.*?)</pre>",
                            Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    String content = copyArticle.getContent();
                    Matcher matcher = pattern.matcher(content);
                    while (matcher.find()) {
                        String preSnipet = matcher.group();
                        String newPreSnipet = preSnipet
                                .replaceAll("&", "&amp;");
                        if (!preSnipet.equals(newPreSnipet)) {
                            content = content.replace(preSnipet, newPreSnipet);
                        }
                    }
                    copyArticle.setContent(content);
                    return copyArticle;
                }
            } catch (NumberFormatException e) {
            }
        }
        Article article = (Article) super.formBackingObject(request);
        article.setAllowReply(true);
        return article;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map replyMap = new HashMap();
        replyMap.put("true", "是");
        replyMap.put("false", "否");
        Map map = new HashMap();
        map.put("replyMap", replyMap);
        map.put("blog", dao.getBlogDao().get());
        map.put("title", "编辑文章");

        String catelogs = "";
        for (Catelog catelog : dao.getCatelogDao().list()) {
            catelogs += "'" + catelog.getName() + "',";
        }
        if (catelogs.length() > 0) {
            catelogs = catelogs.substring(0, catelogs.length() - 1);
        }
        map.put("catelogs", catelogs);
        if (request.getParameter("id") != null) {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                map.put("id", id);
            } catch (NumberFormatException e) {
            }
        }
        return map;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        Catelog oldCatelog = null;
        if (request.getParameter("id") != null) {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                Article oldArticle = dao.getArticleDao().get(id);

                if (!oldArticle.getCatelog().equals(
                        ((Article) command).getCatelog())) {
                    // 修改了catelog
                    oldCatelog = dao.getCatelogDao().get(
                            oldArticle.getCatelog());
                }

                BeanUtils.copyProperties(command, oldArticle);
                command = oldArticle;
            } catch (NumberFormatException e) {
            }
        }

        Article article = (Article) command;
        // System.out.println(article.getContent());
        // System.out.println("a:" + article);
        // System.out.println("key:" + article.getKey());
        // if (article.getKey() != null) {
        // System.out.println("id:" + article.getArticleID());
        // }

        article.setAuthor(dao.getUserService().getCurrentUser());
        article.setDate(new Date(System.currentTimeMillis()));
        // System.out.println(ReflectionToStringBuilder.toString(article,
        // ToStringStyle.MULTI_LINE_STYLE));
        dao.getArticleDao().save(article);

        if (oldCatelog != null) {
            oldCatelog.setCount(oldCatelog.getCount() - 1);
            dao.getCatelogDao().save(oldCatelog);
        }

        Catelog newCatelog = dao.getCatelogDao().get(article.getCatelog());
        if (newCatelog == null) {
            Catelog catelog = new Catelog();
            catelog.setName(article.getCatelog());
            catelog.setCount(1);
            dao.getCatelogDao().save(catelog);
        } else {
            newCatelog.setCount(newCatelog.getCount() + 1);
            dao.getCatelogDao().save(newCatelog);
        }

        response.sendRedirect("/show.html?id=" + article.getArticleID());
        return null;
    }
}

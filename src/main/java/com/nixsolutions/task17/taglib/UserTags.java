package com.nixsolutions.task17.taglib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nixsolutions.task17.model.Role;
import com.nixsolutions.task17.model.User;

/**
 * Class for implementing of Custom Tag for creating User table on Admin page.
 */
public class UserTags implements Tag {

    private final long AGE = 31536000000l;

    private static final Logger logger = LoggerFactory
            .getLogger(UserTags.class);
    PageContext pageContext;
    private List<User> users = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int doStartTag() throws JspException {
        String contextPath = pageContext.getServletContext().getContextPath();
        String dynamicTablePart;
        JspWriter out = pageContext.getOut();
        try {
            for (User user : getUsers()) {
                dynamicTablePart = "<tr>\n" + "<td>" + user.getLogin()
                        + "</td>\n" + "<td>" + user.getFirstName() + "</td>\n"
                        + "<td>" + user.getLastName() + "</td>\n" + "<td>"
                        + calculateAge(user.getBirthday()) + "</td>\n" + "<td>"
                        + user.getRole().getName() + "</td>\n"
                        + "<td><form class=\"form-inline\" role=\"form\" method=\"get\" action='"
                        + contextPath + "/edit/" + user.getId() + "'>"
                        + "<div class=\"form-group\" style=\"float:left;\"><input type=\"submit\""
                        + " class=\"btn btn-primary\" value=\"Edit\"/></div></form>"
                        + "<form action='" + contextPath + "/delete/"
                        + user.getId() + "' method='get' "
                        + "style='display: inline;'><div class=\"form-group\"><input type='submit' "
                        + "class=\"btn btn-default delete_button\""
                        + " value='Delete' "
                        + "onclick=\"return confirm('Are you sure?')\"/></div></form>"
                        + "</tr>";
                out.print(dynamicTablePart);
            }
            out.print("</tbody></table>");
        } catch (Exception e) {
            logger.error("Error during creating custom tag" + e);
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }

    private long calculateAge(Date birthDay) {
        return (new Date().getTime() - birthDay.getTime()) / AGE;
    }

    @Override
    public void setPageContext(PageContext arg0) {
        pageContext = arg0;
    }

    @Override
    public int doEndTag() throws JspException {
        return 0;
    }

    @Override
    public Tag getParent() {
        return null;
    }

    @Override
    public void release() {

    }

    @Override
    public void setParent(Tag arg0) {

    }

}

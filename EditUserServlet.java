package servlet;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {
    private UserDao userDao;
    private static final Logger logger = Logger.getLogger(EditUserServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastLogin = request.getParameter("lastLogin");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String roleId = request.getParameter("roleId");
        logger.debug("User updating: " + login);
        User user = new User(login, password, roleId);
        userDao.updateUser(user, lastLogin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-list.jsp");
        request.setAttribute("userList", userDao.selectAllUser());
        logger.info("Forward to UserList.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        User user = userDao.selectUser(login);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-form.jsp");
        logger.info("Forward to UserForm.jsp");
        dispatcher.forward(request, response);

    }
}

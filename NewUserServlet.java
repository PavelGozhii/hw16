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

@WebServlet("/new")
public class NewUserServlet extends HttpServlet {
    private UserDao userDao;
    private static final Logger logger = Logger.getLogger(NewUserServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("roleId");
        logger.debug("Create " + role + ": " + login);
        User user = new User(login, password, role);
        if (userDao.insertUser(user)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-list.jsp");
            request.setAttribute("userList", userDao.selectAllUser());
            logger.info("Forward to user-list.jsp");
            dispatcher.forward(request, response);
        } else {
            logger.warn("User is not created");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            request.setAttribute("error", "User is already exists");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Forward to user-form.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-form.jsp");
        dispatcher.forward(request, response);
    }
}

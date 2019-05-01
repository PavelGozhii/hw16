package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDao.selectUser(req.getParameter("login"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
        if (user == null) {
            req.setAttribute("error", "UserNotFound");
        } else if (user != null && user.getRoleId().equals("admin")) {
            if (req.getParameter("login").equals(user.getLogin())
                    && req.getParameter("password").equals(user.getPassword())) {
                req.setAttribute("userList", userDao.selectAllUser());
                dispatcher = req.getRequestDispatcher("admin/user-list.jsp");
            } else {
                req.setAttribute("error", "Incorrect login or password");
            }
        } else if (user != null && user.getRoleId().equals("user")) {
            if (req.getParameter("login").equals(user.getLogin())
                    && req.getParameter("password").equals(user.getPassword())) {
                req.setAttribute("login", user.getLogin());
                dispatcher = req.getRequestDispatcher("UserPage.jsp");
            } else {
                req.setAttribute("error", "Incorrect login or password");
            }
        } else {
            req.setAttribute("error", "UnknownUserRole");
        }
        dispatcher.forward(req, resp);
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

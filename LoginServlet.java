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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.selectUser(login);
        if (user == null) {
            showErrorPage(req, resp, "UserNotFound");
        } else if (user.getPassword().equals(password)) {
            req.getSession().setAttribute("login", login);
            switch (user.getRoleId()) {
                case "admin":
                    resp.sendRedirect("/userList");
                    break;
                case "user":
                    resp.sendRedirect("UserPage.jsp");
                    break;
                default:
                    showErrorPage(req, resp, "Unknown role");
            }
        } else {
            showErrorPage(req, resp, "Incorrect password");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    private void showErrorPage(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        request.setAttribute("ref", "index.jsp");
        request.setAttribute("error", error);
        dispatcher.forward(request, response);
    }
}

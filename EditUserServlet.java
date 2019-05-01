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

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {
    UserDao userDao;

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
        User user = new User(login, password, roleId);
        userDao.updateUser(user, lastLogin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-list.jsp");
        request.setAttribute("userList", userDao.selectAllUser());
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        User user = userDao.selectUser(login);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-form.jsp");
        dispatcher.forward(request, response);

    }
}

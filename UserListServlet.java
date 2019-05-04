package servlet;

import dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
    private UserDao userDao;
    private static final Logger logger = Logger.getLogger(UserListServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Forward to user-list.jsp");
        request.setAttribute("userList", userDao.selectAllUser());
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-list.jsp");
        dispatcher.forward(request, response);
    }
}

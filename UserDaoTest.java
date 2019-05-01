import dao.UserDao;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.List;

public class UserDaoTest extends Assert {

    @Rule
    public final Timeout timeout = new Timeout(1000);
    UserDao userDao = new UserDao();

    @Before
    public void beforeUserDaoTest() {
        User user = new User("testLogin", "12345", "user");
        userDao.insertUser(user);
    }

    @After
    public void afterUserDaoTest() {
        userDao.deleteUser("testLogin");
    }

    @Test
    public void insertUserDaoTest() {
        User newUser = userDao.selectUser("testLogin");
        assertEquals("testLogin", newUser.getLogin());
        assertEquals("12345", newUser.getPassword());
        assertEquals(null, userDao.selectUser("newTestLogin"));
    }

    @Test
    public void isExistUserDaoTest() {
        assertEquals(true, userDao.isExists("testLogin"));
        assertEquals(false, userDao.isExists("notExistsTestLogin"));
    }

    @Test
    public void selectAllUserDaoTest() {
        List<User> userList = userDao.selectAllUser();
        assertNotNull(userList);
    }

    @Test
    public void deleteUserDaoTest() {
        userDao.deleteUser("testLogin");
        assertEquals(false, userDao.isExists("testLogin"));
    }

    @Test
    public void updateUserDaoTest() {
        User user = new User("updateTestLogin", "54321", "user");
        assertEquals(true, userDao.updateUser(user, "testLogin"));
        User updateUser = userDao.selectUser("updateTestLogin");
        assertEquals(updateUser.getLogin(), "updateTestLogin");
        assertEquals(updateUser.getPassword(), "54321");
        userDao.deleteUser("updateTestLogin");
    }
}
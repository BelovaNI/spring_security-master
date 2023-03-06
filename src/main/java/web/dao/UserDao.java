package web.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;
import java.util.List;

public interface UserDao {
    User getUserByName(String name);
    void add(User user);
    List<User> listUsers();
    void delete(User user);

}

package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User getUserByName(String name);
    void add(User user);
    List<User> listUsers();
}

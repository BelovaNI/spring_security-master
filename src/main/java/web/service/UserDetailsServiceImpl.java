package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @PostConstruct
    public void addUser(){
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        User user1 = new User("user", "1234", Collections.singleton(role1));
        User admin = new User("admin", "qwerty", Collections.singleton(role2));
        userDao.add(user1);
        userDao.add(admin);
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUserByName(s);
    }
}

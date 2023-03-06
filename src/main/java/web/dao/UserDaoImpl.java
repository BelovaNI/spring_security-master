package web.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {


    //    singletonMap (): этот метод возвращает неизменяемую карту , содержащую только указанное сопоставление ключ-значение:
//    private final Map<String, User> userMap = Collections.singletonMap("test",
//            new User(1L, "test", "test", Collections.singleton(new Role(1L, "ROLE_USER"))));
// name - уникальное значение, выступает в качестве ключа Map
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    @Override
    @Transactional
    public void add(User user) {
        hibernateTemplate.save(user);
    }

    @Override
    public List<User> listUsers() {
        return hibernateTemplate.execute(session -> session.createQuery("from User").list());
    }


    @Override
    @SuppressWarnings("unchecked")
    public User getUserByName(String name) {
        String hql = "FROM User user WHERE user.name = :name";
        List<User> users = (List<User>) hibernateTemplate.execute(session -> {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setMaxResults(1);
            return query.list();
        });
        return !users.isEmpty() ? users.get(0) : null;
    }
}


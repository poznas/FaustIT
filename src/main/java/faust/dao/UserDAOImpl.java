package faust.dao;

import faust.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(
                "from User order by username", User.class);

        return query.getResultList();
    }

    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    public void deleteUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from User where id=:userId");
        query.setParameter("userId", userId);

        query.executeUpdate();
    }

    public User getUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }

    public void updatePassword(int userId, String hashedPassword) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update User u set u.password = :hashedPassword where u.id=:userId");
        query.setParameter("userId", userId);
        query.setParameter("hashedPassword", hashedPassword);

        query.executeUpdate();
    }

    @Override
    public User getUser(String userName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.equal(root.get("username"), userName));
        Query<User> query = session.createQuery(criteria);

        return query.getSingleResult();
    }
}

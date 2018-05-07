package faust.dao;

import faust.entity.Group;
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
public class GroupDAOImpl implements GroupDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public GroupDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Group> getGroups() {
        Session session = sessionFactory.getCurrentSession();
        Query<Group> query = session.createQuery(
                "from Group order by groupName", Group.class);

        return query.getResultList();
    }

    public Group getGroup(int groupId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Group.class, groupId);
    }

    public void saveGroup(Group group) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(group);
    }

    public void deleteGroup(int groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Group where id=:groupId");
        query.setParameter("groupId", groupId);

        query.executeUpdate();
    }

    @Override
    public Group getGroup(String userGroupName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Group> criteria = builder.createQuery(Group.class);
        Root<Group> root = criteria.from(Group.class);
        criteria.select(root).where(builder.equal(root.get("groupName"), userGroupName));
        Query<Group> query = session.createQuery(criteria);

        return query.getSingleResult();
    }
}

package com.crud.spring.dao;

import com.crud.spring.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User saved. User details: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        //session.update(user);
        User existingUser = (User)session.get(User.class, user.getId());
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setAdmin(user.getisAdmin());
        existingUser.setCreatedDate(user.getCreatedDate());
        session.save(existingUser);
        logger.info("User udate. User details: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user!=null){
            session.delete(user);
        }
        logger.info("User removed. User details: " + user);
    }

    @Override
    public User getUserId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded. User details: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();

        for (User user: userList){
            logger.info("User list: "+user);
        }

        return userList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT e FROM User e WHERE e.name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name",userName);
        List<User> userList = query.list();

        for (User user: userList){
            logger.info("User list: "+user);
        }

        return userList;
    }


}

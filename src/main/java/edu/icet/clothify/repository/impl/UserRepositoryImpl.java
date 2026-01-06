package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.User;
import edu.icet.clothify.repository.UserRepository;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserRepositoryImpl implements UserRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();
    User user = new User();

    @Override
    public boolean addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        return true;
    }

    @Override
    public User getUser(String email) {
        try {
            String hql = "FROM User WHERE email = :userEmail";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("userEmail", email);
            return query.uniqueResult();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return null;
        }
    }
}

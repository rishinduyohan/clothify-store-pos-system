package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.Category;
import edu.icet.clothify.repository.InventoryRepository;
import org.hibernate.Session;

import java.util.List;

public class InventoryRepositoryImpl implements InventoryRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Category> getCategories() {
        return session.createQuery("From Category",Category.class).list();
    }
}

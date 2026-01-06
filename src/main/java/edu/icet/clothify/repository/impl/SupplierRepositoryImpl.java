package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.Supplier;
import edu.icet.clothify.repository.SupplierRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Supplier> getSuppliers() {
        return session.createQuery("From Supplier", Supplier.class).list();
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        Transaction transaction = session.beginTransaction();
        session.persist(supplier);
        transaction.commit();
        return true;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        Transaction transaction = session.beginTransaction();
        session.merge(supplier);
        transaction.commit();
        return true;
    }

    @Override
    public boolean removeSupplier(Supplier supplier) {
        Transaction transaction = session.beginTransaction();
        session.remove(supplier);
        transaction.commit();
        return true;
    }
}

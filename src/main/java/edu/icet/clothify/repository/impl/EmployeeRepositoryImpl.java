package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.Employee;
import edu.icet.clothify.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;

    @Override
    public List<Employee> getAllEmployees() {
       return session.createQuery("From Employee",Employee.class).list();
    }
}

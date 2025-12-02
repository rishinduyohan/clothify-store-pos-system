package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.Employee;
import edu.icet.clothify.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Employee> getAllEmployees() {
       return session.createQuery("From Employee",Employee.class).list();
    }

    @Override
    public boolean addEmployee(Employee employee) {
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        return true;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        Transaction transaction = session.beginTransaction();
        session.remove(employee);
        transaction.commit();
        return true;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Transaction transaction = session.beginTransaction();
        session.merge(employee);
        transaction.commit();
        return true;
    }

    @Override
    public Employee searchEmployee(Long id) {
        return session.find(Employee.class,id);
    }
}

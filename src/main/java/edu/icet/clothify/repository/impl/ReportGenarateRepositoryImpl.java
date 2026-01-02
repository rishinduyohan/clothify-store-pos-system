package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.repository.ReportGenarateRepository;
import org.hibernate.Session;

public class ReportGenarateRepositoryImpl implements ReportGenarateRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Session getSalesConnection() {
        return session;
    }
}

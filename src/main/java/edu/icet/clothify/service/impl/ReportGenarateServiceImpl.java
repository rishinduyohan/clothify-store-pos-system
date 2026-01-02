package edu.icet.clothify.service.impl;

import edu.icet.clothify.repository.ReportGenarateRepository;
import edu.icet.clothify.repository.impl.ReportGenarateRepositoryImpl;
import edu.icet.clothify.service.ReportGenarateService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReportGenarateServiceImpl implements ReportGenarateService {
    ReportGenarateRepository reportGenarateRepository = new ReportGenarateRepositoryImpl();
    Session session = null;

    @Override
    public boolean dailySalesReport(String date) {
        session = reportGenarateRepository.getDailySalesConnection();
        if (null != session) {
            session.doReturningWork(connection -> {
                try {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("target_date", date);

                    InputStream reportStream = getClass().getResourceAsStream("/reports/daily_sales_summary.jrxml");

                    JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
                    JasperViewer.viewReport(jasperPrint, false);
                    return true;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            dailySalesReport(date);
        }
        return false;
    }
}

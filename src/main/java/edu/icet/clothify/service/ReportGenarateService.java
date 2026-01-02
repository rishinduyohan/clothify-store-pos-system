package edu.icet.clothify.service;

public interface ReportGenarateService {
    boolean dailySalesReport(String date);
    boolean monthlySalesReport(int month,int year);
}

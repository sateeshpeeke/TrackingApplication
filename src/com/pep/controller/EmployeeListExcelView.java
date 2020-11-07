package com.pep.controller;

import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import com.pep.pojo.UserRegistration;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.Map;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class EmployeeListExcelView extends AbstractExcelView
{
    protected void buildExcelDocument(final Map<String, Object> model, final HSSFWorkbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final HSSFSheet excelSheet = workbook.createSheet("Employee List");
        final HSSFCellStyle my_style = workbook.createCellStyle();
        this.setExcelHeader(excelSheet, my_style);
        final List<UserRegistration> employeeList = (List<UserRegistration>) model.get("employeeList");
        this.setExcelRows(excelSheet, employeeList);
    }
    
    public void setExcelHeader(final HSSFSheet excelSheet, final HSSFCellStyle my_style) {
        final HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("FirstName");
        excelHeader.createCell(1).setCellValue("LastName");
        excelHeader.createCell(2).setCellValue("EmployeeId");
        excelHeader.createCell(3).setCellValue("GpId");
        excelHeader.createCell(4).setCellValue("MobileNumber");
        excelHeader.createCell(5).setCellValue("TcsMail");
        excelHeader.createCell(6).setCellValue("PepsicoMail");
        excelHeader.createCell(7).setCellValue("Cluster");
        excelHeader.createCell(8).setCellValue("SubCluster");
        excelHeader.createCell(9).setCellValue("Reporting To");
        excelHeader.createCell(10).setCellValue("PrimarySkils");
        excelHeader.createCell(11).setCellValue("SecondarySkils");
        excelHeader.createCell(12).setCellValue("Role");
    }
    
    public void setExcelRows(final HSSFSheet excelSheet, final List<UserRegistration> employeeList) {
        int record = 1;
        for (final UserRegistration user : employeeList) {
            final HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(user.getFirstName());
            excelRow.createCell(1).setCellValue(user.getLastName());
            excelRow.createCell(2).setCellValue(user.getEmpId());
            excelRow.createCell(3).setCellValue(user.getGpId());
            excelRow.createCell(4).setCellValue(user.getMobileNumber());
            excelRow.createCell(5).setCellValue(user.getTcsMail());
            excelRow.createCell(6).setCellValue(user.getPepsicoMail());
            excelRow.createCell(7).setCellValue(user.getCluster());
            excelRow.createCell(8).setCellValue(user.getSubCluster());
            excelRow.createCell(9).setCellValue(user.getReportingTo());
            excelRow.createCell(10).setCellValue(user.getPrimarySkils());
            excelRow.createCell(11).setCellValue(user.getSecondarySkils());
            excelRow.createCell(12).setCellValue(user.getRole());
        }
    }
}
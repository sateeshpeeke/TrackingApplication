package com.pep.controller;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.GregorianCalendar;
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

public class EmployeeLeavesListExcelView extends AbstractExcelView
{
    protected void buildExcelDocument(final Map<String, Object> model, final HSSFWorkbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final short datestyle = workbook.createDataFormat().getFormat("yyyy-mm-dd");
        final HSSFSheet excelSheet = workbook.createSheet("Employee Leave List");
        final HSSFCellStyle my_style = workbook.createCellStyle();
        my_style.setDataFormat(datestyle);
        this.setExcelHeader(excelSheet, my_style);
        final List<UserRegistration> employeeLeaveList = (List<UserRegistration>) model.get("employeeLeaveList");
        this.setExcelRows(excelSheet, employeeLeaveList);
    }
    
    public void setExcelHeader(final HSSFSheet excelSheet, final HSSFCellStyle my_style) {
        final HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("FirstName");
        excelHeader.createCell(1).setCellValue("LastName");
        excelHeader.createCell(2).setCellValue("EmployeeId");
        excelHeader.createCell(3).setCellValue("Cluster");
        excelHeader.createCell(4).setCellValue("SubCluster");
        excelHeader.createCell(5).setCellValue("LeaveType");
        excelHeader.createCell(6).setCellValue("StartDate");
        excelHeader.createCell(7).setCellValue("EndDate");
        excelHeader.createCell(8).setCellValue("NoOfDays");
        excelHeader.createCell(9).setCellValue("CompOffWorkedDate");
        excelHeader.createCell(10).setCellValue("Reason");
    }
    
    public void setExcelRows(final HSSFSheet excelSheet, final List<UserRegistration> employeeLeaveList) {
        int record = 1;
        for (final UserRegistration user : employeeLeaveList) {
            final HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(user.getFirstName());
            excelRow.createCell(1).setCellValue(user.getLastName());
            excelRow.createCell(2).setCellValue(user.getEmpId());
            excelRow.createCell(3).setCellValue(user.getCluster());
            excelRow.createCell(4).setCellValue(user.getSubCluster());
            excelRow.createCell(5).setCellValue(user.getLeaveType());
            excelRow.createCell(6).setCellValue(user.getStartDate().toString());
            excelRow.createCell(7).setCellValue(user.getEndDate().toString());
            excelRow.createCell(8).setCellValue((double)user.getNoOfDays());
            if (user.getCompoffWorkedDate() != null) {
                final Calendar calendar = new GregorianCalendar();
                calendar.setTime(user.getCompoffWorkedDate());
                final int year = calendar.get(1);
                if (year >= 2019) {
                    excelRow.createCell(9).setCellValue(user.getCompoffWorkedDate().toString());
                }
                else {
                    excelRow.createCell(9).setCellValue("");
                }
            }
            else {
                excelRow.createCell(9).setCellValue("");
            }
            if (user.getReason().equals("null")) {
                excelRow.createCell(10).setCellValue("");
            }
            else {
                excelRow.createCell(10).setCellValue(user.getReason());
            }
        }
    }
}
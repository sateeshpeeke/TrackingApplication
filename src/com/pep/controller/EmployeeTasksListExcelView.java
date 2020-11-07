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

public class EmployeeTasksListExcelView extends AbstractExcelView
{
    protected void buildExcelDocument(final Map<String, Object> model, final HSSFWorkbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final short datestyle = workbook.createDataFormat().getFormat("yyyy-mm-dd");
        final HSSFSheet excelSheet = workbook.createSheet("Employee Task List");
        final HSSFCellStyle my_style = workbook.createCellStyle();
        my_style.setDataFormat(datestyle);
        this.setExcelHeader(excelSheet, my_style);
        final List<UserRegistration> employeeTaskList = (List<UserRegistration>) model.get("employeeTaskList");
        this.setExcelRows(excelSheet, employeeTaskList);
    }
    
    public void setExcelHeader(final HSSFSheet excelSheet, final HSSFCellStyle my_style) {
        final HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("FirstName");
        excelHeader.createCell(1).setCellValue("LastName");
        excelHeader.createCell(2).setCellValue("EmployeeId");
        excelHeader.createCell(3).setCellValue("Cluster");
        excelHeader.createCell(4).setCellValue("SubCluster");
        excelHeader.createCell(5).setCellValue("Shift Date");
        excelHeader.createCell(6).setCellValue("Shift Start Time");
        excelHeader.createCell(7).setCellValue("Shift End Time");
        excelHeader.createCell(8).setCellValue("Task Type");
        excelHeader.createCell(9).setCellValue("Task Id");
        excelHeader.createCell(10).setCellValue("Task Description");
    }
    
    public void setExcelRows(final HSSFSheet excelSheet, final List<UserRegistration> employeeTaskList) {
        int record = 1;
        for (final UserRegistration user : employeeTaskList) {
            final HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(user.getFirstName());
            excelRow.createCell(1).setCellValue(user.getLastName());
            excelRow.createCell(2).setCellValue(user.getEmpId());
            excelRow.createCell(3).setCellValue(user.getCluster());
            excelRow.createCell(4).setCellValue(user.getSubCluster());
            excelRow.createCell(5).setCellValue(user.getStartDate().toString());
            excelRow.createCell(6).setCellValue(user.getPickUp());
            excelRow.createCell(7).setCellValue(user.getDropTime());
            excelRow.createCell(8).setCellValue(user.getTaskType());
            excelRow.createCell(9).setCellValue(user.getTaskId());
            excelRow.createCell(10).setCellValue(user.getTaskDescription());
        }
    }
}
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

public class EmployeeRostersListExcelView extends AbstractExcelView
{
    protected void buildExcelDocument(final Map<String, Object> model, final HSSFWorkbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final short datestyle = workbook.createDataFormat().getFormat("yyyy-mm-dd");
        final HSSFSheet excelSheet = workbook.createSheet("Employee Roster List");
        final HSSFCellStyle my_style = workbook.createCellStyle();
        my_style.setDataFormat(datestyle);
        this.setExcelHeader(excelSheet, my_style);
        final List<UserRegistration> employeeRosterList = (List<UserRegistration>) model.get("employeeRosterList");
        this.setExcelRows(excelSheet, employeeRosterList);
    }
    
    public void setExcelHeader(final HSSFSheet excelSheet, final HSSFCellStyle my_style) {
        final HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("FirstName");
        excelHeader.createCell(1).setCellValue("LastName");
        excelHeader.createCell(2).setCellValue("EmployeeId");
        excelHeader.createCell(3).setCellValue("Roster Date");
        excelHeader.createCell(4).setCellValue("PickUp");
        excelHeader.createCell(5).setCellValue("Drop");
        excelHeader.createCell(6).setCellValue("Location");
    }
    
    public void setExcelRows(final HSSFSheet excelSheet, final List<UserRegistration> employeeRosterList) {
        int record = 1;
        for (final UserRegistration user : employeeRosterList) {
            final HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(user.getFirstName());
            excelRow.createCell(1).setCellValue(user.getLastName());
            excelRow.createCell(2).setCellValue(user.getEmpId());
            excelRow.createCell(3).setCellValue(user.getRosterDate().toString());
            excelRow.createCell(4).setCellValue(user.getPickUp());
            excelRow.createCell(5).setCellValue(user.getDropTime());
            excelRow.createCell(6).setCellValue(user.getLocation());
        }
    }
}
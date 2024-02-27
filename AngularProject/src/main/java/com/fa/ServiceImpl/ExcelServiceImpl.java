//package com.fa.ServiceImpl;
//
//import javax.annotation.Resource;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Service;
//
//import com.fa.Dto.AngularDto1;
//import com.fa.Dto.CommonDto;
//import com.fa.Entity.AngularEntity;
//import com.fa.Repository.AngularRepository;
//
//@Service
//public class ExcelServiceImpl {
//
//	@Resource
//	AngularRepository angularRepository;
//	 private XSSFWorkbook workbook;
//	    private XSSFSheet sheet;
//	public CommonDto excel(AngularDto1 dto) {
//		CommonDto commonDto=new CommonDto();
//		AngularEntity data = angularRepository.getById(dto.getId());
//		
//		sheet = workbook.createSheet("Users");
//        
//        Row row = sheet.createRow(0);
//         
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight(16);
//        style.setFont(font);
//         
//        createCell(row, 0, "ID", style);      
//        createCell(row, 1, "Email", style);       
//        createCell(row, 2, "User", style);    
//        createCell(row, 3, "CreatedBy", style);
//        createCell(row, 4, "CreatedDate", style);
//		
//		return null;
//	}
//	private void createCell(Row row,int columnCount, int i, String string, CellStyle style) {
//		
//		sheet.autoSizeColumn(columnCount);
//        Cell cell = row.createCell(columnCount);
//        if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        }else {
//            cell.setCellValue((String) value);
//        }
//        cell.setCellStyle(style);
//	}
//
//}

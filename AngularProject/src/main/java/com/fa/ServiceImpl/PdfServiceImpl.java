package com.fa.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fa.Dto.AngularDto1;
import com.fa.Dto.CommonDto;
import com.fa.Entity.AngularEntity;
import com.fa.Repository.AngularRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

@Service
public class PdfServiceImpl  {

	@Resource
	AngularRepository angularRepository;
	
	public CommonDto pdf(AngularDto1 dto) {
		CommonDto commonDto=new CommonDto();
		AngularEntity data = angularRepository.getById(dto.getId());
		System.err.println("In PDF");
		String filepath = "D:\\sample.pdf";
//		String para1 = "Hello EveryOne this is Test PDF";
		try {
		PdfWriter writer=new PdfWriter(filepath);
		
		PdfDocument pdfdoc=new PdfDocument(writer);
		pdfdoc.addNewPage();
		
//		Paragraph p1 = new Paragraph(para1);
		// Creating a table       
	    float [] pointColumnWidths = {1.5f, 3.5f,3.0f, 1.5f,3.5f};   
	    Table table = new Table(pointColumnWidths);
	    table.setWidthPercent(100f);
	    
	    // Adding cells to the table       
	    table.addCell(new Cell().add("ID"));       
	    table.addCell(new Cell().add("User"));       
//	    table.addCell(new Cell().add("Password"));
	    table.addCell(new Cell().add("Email"));
	    table.addCell(new Cell().add("Created By"));
	    table.addCell(new Cell().add("Created Date"));
//	    long i=data.getId();
//	    String s=String.valueOf(i);
	    table.addCell(new Cell().add(String.valueOf(data.getId())));
	    table.addCell(new Cell().add(data.getUser()));
//	    table.addCell(new Cell().add(data.getPassword()));
	    table.addCell(new Cell().add(data.getEmail()));       
	    table.addCell(new Cell().add(data.getCreatedBy()));
	    table.addCell(new Cell().add(data.getCreatedDate()));
		
		Document document=new Document(pdfdoc);
//		document.add(p1);
		 // Adding Table to document        
	    document.add(table); 
		
		document.close();
		commonDto.setStatus("Download Successfuly");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return commonDto;
	
	}
}

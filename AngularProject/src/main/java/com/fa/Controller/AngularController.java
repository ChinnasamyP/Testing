package com.fa.Controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.PostLoad;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fa.Dto.AngularDto1;
import com.fa.Dto.CommonDto;
import com.fa.Entity.Course;
import com.fa.Entity.Teacher;
import com.fa.Repository.StudentRepository;
import com.fa.Service.AngularService;
import com.fa.ServiceImpl.PdfServiceImpl;
import com.fa.constants.CreationConstants;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class AngularController 
{
	@Resource
	AngularService angularService;
	
	@Resource 
	PdfServiceImpl pdfServiceImpl;
	

//	
//	@Resource
//	ExcelServiceImpl excelService;
//	 
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<?> form(@RequestBody AngularDto1 angularDto)
	{
		 CommonDto commonDto=angularService.save1(angularDto);
		return new ResponseEntity<CommonDto>(commonDto,HttpStatus.OK);	
	}

	@RequestMapping(value ="/showData", method=RequestMethod.GET)
	public ResponseEntity<?>showData(){
		
		CommonDto dto = angularService.getdata();
		return new ResponseEntity<CommonDto>(dto,HttpStatus.OK);
		
	}
	
	@RequestMapping(value ="/editSave", method=RequestMethod.POST)
	public ResponseEntity<?>editSave(@RequestBody AngularDto1 dto1){
		
		CommonDto dto = angularService.edit(dto1);
		System.err.println(dto1.getCreatedBy());
		
		return new ResponseEntity<CommonDto>(dto,HttpStatus.OK);
		
	}
	
//	@RequestMapping(value="/delete",method=RequestMethod.POST)
//	public ResponseEntity<CommonDto> delete(@RequestBody AngularDto1 dto){
//
//	 CommonDto commonDto1 = angularService.deleteByName(dto);
//	 return new ResponseEntity<CommonDto>(commonDto1,HttpStatus.OK);
//	}
	

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> loginuser(@RequestBody AngularDto1 dto){

	 CommonDto commonDto1 = angularService.login(dto);
	 return new ResponseEntity<CommonDto>(commonDto1,HttpStatus.OK);
	}
	
	@RequestMapping(value="/editPage",method=RequestMethod.POST)
	public ResponseEntity<?> editPage(@RequestParam("id")Long id){
		System.err.println(id);
	 CommonDto commonDto1 = angularService.editPage(id);
	 
	 return new ResponseEntity<CommonDto>(commonDto1,HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/pdf", method = RequestMethod.POST)
	public ResponseEntity<?> pdf(@RequestBody AngularDto1 dto ){
		CommonDto commonDto = pdfServiceImpl.pdf(dto);
		return new ResponseEntity<CommonDto>(commonDto, HttpStatus.OK);
	}
	
	@RequestMapping(path="/excel", method = RequestMethod.POST)
	public ResponseEntity<?> excel(@RequestBody AngularDto1 dto ){
		CommonDto commonDto = angularService.excel(dto);
		return new ResponseEntity<CommonDto>(commonDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/file",method=RequestMethod.GET)
	public ModelAndView creation(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView ref4=new ModelAndView("File");
		return ref4;
		
	}
	@RequestMapping(value="/uploadexcel/showNullColumn",method=RequestMethod.GET)
	public ModelAndView showEmptyCell(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView m1=new ModelAndView("EmptyColumn");
		CommonDto commonDto=new CommonDto();
		List<String> emptyCol=commonDto.getModel();
		m1.addObject("Values",emptyCol);
		return m1;	
	}
	

	@RequestMapping(value ="/uploadexcel",method = RequestMethod.POST)
//	public ResponseEntity<CommonDto> bulkupload(@RequestParam MultipartFile file) throws Exception {
	public ModelAndView bulkUpload(@RequestParam MultipartFile file) throws Exception{
		System.err.println("excel");
		ModelAndView m1=new ModelAndView("EmptyColumn");
		CommonDto commonDto=angularService.uploadExcelFile(file);
		List<String> emptyCol=commonDto.getModel();
		m1.addObject("Values",emptyCol);
//		return new ResponseEntity<CommonDto>(commonDto, HttpStatus.OK);	
		return m1;
	}
	
//	@GetMapping("/download")
//	public ResponseEntity<Resource> getFile(){
//		String filename="result.xlsx";
//		InputStreamResource file=new InputStreamResource(angularService.load());
//		
//		return null;
//	}
	
	
	@GetMapping(value = "/student") 
	public ResponseEntity<?> student() {
		System.err.println("--------");
		angularService.student();
		return new  ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getStudent") 
	public ResponseEntity<?> getStudent() {
		String name= "raj";
				
	
		return new  ResponseEntity<>(name,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStudentByPage")
	public ResponseEntity<?> getStudentByPage(Integer page){
		CommonDto commonDto	=angularService.getStudentByPage(page);
	return new  ResponseEntity<>(commonDto,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCourse")
	public ResponseEntity<?> getCourse(@RequestBody Course course){
		CommonDto commonDto	=angularService.getCourse(course);
	return new  ResponseEntity<>(commonDto,HttpStatus.OK);
	}
	
	@PostMapping(value = "/saveTeacher")
	public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher){
		CommonDto commonDto	=angularService.saveTeacher(teacher);
		commonDto.setMessage("Success");
	return new  ResponseEntity<>(commonDto,HttpStatus.OK);
	}
	
	@GetMapping(value = "/jasperReport")
	private void downloadAuthLetter(HttpServletRequest httpServletRequest) {
		
		
		
		
//		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(authDtoList);
//		JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/authorletter.jrxml"));
//		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//		Map<String, Object> parameters = new HashMap<>();
//		JasperPrint fillReport = JasperFillManager.fillReport(jasperReport, parameters, datasource);
//		byte[] data = JasperExportManager.exportReportToPdf(fillReport);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		baos.write(data, 0, data.length);
//		fileDownloadDto.setBaos(baos);
//		fileDownloadDto.setFileName("Authorization_Letter");
//	
		
		
		
		
		 try {
		
		final String invoice_template_path = "/jasper/Debite_Note_A4.jrxml";
		
	      File pdfFile = File.createTempFile("my-invoice", ".pdf");
	      FileOutputStream pos = new FileOutputStream(pdfFile);
	       
				// Load the invoice jrxml template.
	        	 final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template_path);
	             final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

	             
	        	
	            final JasperReport report =  JasperCompileManager.compileReport(jasperDesign);

	              // Create parameters map.
	            
	            
	            final Map<String, Object> parameters = new HashMap<>();

	            parameters.put("name","java");
	            parameters.put("id",  "142");
	            parameters.put("author", "james");
	            

	            // Create an empty datasource.
	            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

	            // Render the PDF file
	            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	@GetMapping(value = "/jasperPDFReport")
	public void jasperPDFReport(
			HttpServletRequest httpServletRequest, HttpServletResponse res) {
		
		try {
		AngularDto1 responseDto = angularService.jasperPDFReport("");
		
		byte[] bytes = responseDto.getResponseData().getBaos();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(bytes);
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment; filename="+"File Report.pdf");
	
	
		OutputStream os = res.getOutputStream();
		
		baos.writeTo(os);
		os.flush();
		os.close();
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}

		
//		return new ResponseEntity<>(responseDto.getResponseData().getBaos(), HttpStatus.OK);

	}
	
}

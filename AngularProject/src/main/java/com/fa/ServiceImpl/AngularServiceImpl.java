package com.fa.ServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.fa.CustomException.PageException;
import com.fa.Dto.AngularDto1;
import com.fa.Dto.CommonDto;
import com.fa.Dto.DebitNoteDto;
import com.fa.Dto.FileDownloadDto;
import com.fa.Entity.AngularEntity;
import com.fa.Entity.Course;
import com.fa.Entity.CourseMaterial;
import com.fa.Entity.GetterSetterExample;
import com.fa.Entity.Student;
import com.fa.Entity.Teacher;
import com.fa.Repository.AngularRepository;
import com.fa.Repository.CourseMaterialRepository;
import com.fa.Repository.CourseRepository;
import com.fa.Repository.StudentRepository;
import com.fa.Repository.TeacherRepository;
import com.fa.Service.AngularService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class AngularServiceImpl implements AngularService {

	@Resource
	AngularRepository angularRepository;
	
	@Resource
	StudentRepository studentRepository;
	
	@Resource
	CourseRepository courseRepository;
	
	@Resource
	CourseMaterialRepository courseMaterialRepository;
	
	
	@Resource
	TeacherRepository teacherRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	

	@Override
	public CommonDto save1(AngularDto1 angularDto) {
		CommonDto commonDto = new CommonDto();
		java.util.Base64.Encoder encoder = java.util.Base64.getUrlEncoder();  
        // Encoding URL  
        String encode = encoder.encodeToString(angularDto.getPassword().getBytes());  
        System.out.println("Encoded URL: "+encode);
		try {
				AngularEntity email=angularRepository.findByEmail(angularDto.getEmail());
				
				if(email!=null)
				{
					commonDto.setStatus("Failed");
				}else
				{
					AngularEntity entity = new AngularEntity();
//					entity.setEmpid("FAS-"+angularRepository.getEmpId());
					entity.setEmail(angularDto.getEmail());
					//entity.setPassword(angularDto.getPassword());
					entity.setPassword(encode);
					entity.setUser(angularDto.getUser());
					entity.setCreatedBy(angularDto.getCreatedBy());
					entity.setCreatedDate(angularDto.getCreatedDate());
					angularRepository.save(entity);
					
					commonDto.setStatus("success");
				}
				
					} catch (Exception e) {
			commonDto.setStatus("Exception Occurs....");
		}
		return commonDto;
	}

	@Override
	public CommonDto getdata() {

		List<AngularEntity> list = angularRepository.findAll();
		List<AngularDto1> dtolist= new ArrayList<AngularDto1>();
		CommonDto dto1 = new CommonDto();
		for(AngularEntity data:list)
		{
			AngularDto1 dto=new AngularDto1();
			dto.setId(data.getId());
			dto.setEmail(data.getEmail());
			dto.setPassword(data.getPassword());
			dto.setCreatedBy(data.getCreatedBy());
			dto.setCreatedDate(data.getCreatedBy());
			dto.setUser(data.getUser());
			
			dtolist.add(dto);
		}
		try {
			dto1.setResponseDto(list);
		} catch (Exception e) {
			dto1.setResponseDto(null);
		}
		return dto1;
	}

	@Override
	public CommonDto edit(AngularDto1 dto) {
		CommonDto commonDto = new CommonDto();
		AngularEntity entity = angularRepository.getDataById(dto.getId());
		try {
			entity.setId(dto.getId());
			entity.setEmail(dto.getEmail());
			entity.setPassword(dto.getPassword());
			entity.setCreatedBy(dto.getCreatedBy());
			entity.setCreatedDate(dto.getCreatedDate());
			angularRepository.save(entity);

			commonDto.setStatus("success");
		} catch (Exception e) {
			e.printStackTrace();
			commonDto.setStatus("Exception Occurs..");
		}
		return commonDto;
	}

//	@Override
//	public CommonDto deleteByName(AngularDto1 dto) {
//		CommonDto commonDto = new CommonDto();
//		try {
//			Long id = angularRepository.getIdByName(dto.getName());
//			angularRepository.deleteById(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return commonDto;
//	}

	@Override
	public CommonDto login(AngularDto1 dto) {
		CommonDto commonDto = new CommonDto();
		AngularEntity data = angularRepository.singleData(dto.getEmail());
		java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();  
        // Decoding URl  
        String decode = new String(decoder.decode(data.getPassword()));  
        System.out.println("Decoded URL: "+decode);  
		try {
			if (dto.getEmail().equalsIgnoreCase(angularRepository.getEmailvalue(dto.getEmail()))
					&& decode.equals(dto.getPassword())) {
				AngularDto1 dto1=new AngularDto1();
				dto1.setId(data.getId());
				dto1.setUser(data.getUser());
				dto1.setCreatedBy(data.getCreatedBy());
				dto1.setCreatedDate(data.getCreatedDate());
				dto1.setEmail(data.getEmail());
				dto1.setPassword(decode);
				commonDto.setResponseData(dto1);
				commonDto.setStatus("success");
			}
			else
			{
				commonDto.setStatus("Failed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			commonDto.setStatus("Exception");
		}
		return commonDto;
	}

	@Override
	public CommonDto editPage(Long id) {
		CommonDto dto=new CommonDto();
		
		try {
				AngularEntity entity=angularRepository.getEditpage(id);
				dto.setResponseData(entity);
		}
		catch(Exception e)
		{
			dto.setResponseData(null);
		}
		return dto;
	}

	@Override
	public CommonDto uploadExcelFile(MultipartFile file) {
		
		CommonDto commonDto = new CommonDto();
		List<AngularDto1> dtolist= new ArrayList<AngularDto1>();
		try {
			System.err.println(file);
			InputStream inputStream = file.getInputStream();
			XSSFWorkbook wbook =new XSSFWorkbook (inputStream);
			XSSFSheet sheet=wbook.getSheetAt (0);
			int lastRowNum = sheet.getLastRowNum ();
			int physicalNumberofRows = sheet.getPhysicalNumberOfRows ();
			System.out.println("Inclusive of header: "+ physicalNumberofRows);
			System.out.println("No.of Rows: "+ lastRowNum);
			short lastCellNum=sheet.getRow(0).getLastCellNum();
			System.out.println("No.of cells:"+lastCellNum);
			AngularEntity entity = new AngularEntity();
			AngularDto1 dto=new AngularDto1();
			List<String> emptyCheck=new ArrayList<String>();
			for (int i=1;i<=lastRowNum; i++){
		
				XSSFRow row=sheet.getRow(i);
				for (int j=0; j< lastCellNum; j++){
					XSSFCell cell=row.getCell(j);
					DataFormatter dft=new DataFormatter();
					String value=dft.formatCellValue(cell);
					
					int k=0;
					if(k==j) {
						AngularEntity email=angularRepository.findByEmail(value);
						if(email!=null)
						{
							System.err.println("Email Id is Duplicate! Please set New email!");
							break;
						}
						else entity.setEmail(value);
					}
					else
					{
						switch(j) {
						case 0:
							entity.setEmail(value);
							break;
						case 1:
							if(value.isEmpty()) {
								entity.setUser("User Is Null");
								emptyCheck.add("User Is Null");
							}else
							entity.setUser(value);
							break;
						case 2:
							if(value.isEmpty()) {
								entity.setPassword("Password is Null");
								emptyCheck.add("Password Is Null");
							}else
							entity.setPassword(value);
							break;
						case 3:
							if(value.isEmpty()) {
								entity.setCreatedBy("CreatedBy is Null");
								emptyCheck.add("CreatedBy Is Null");
							}else
							entity.setCreatedBy(value);
							break;
						case 4:
							if(value.isEmpty()) {
								entity.setCreatedDate("CreatedDate is Null");
								emptyCheck.add("CreatedDate Is Null");
							}else
							entity.setCreatedDate(value);
							break;
						case 5:
							if(value.isEmpty()) {
								entity.setAccountNumber("AccountNumber is Null");
								emptyCheck.add("AccountNumber Is Null");
							}else
							entity.setAccountNumber(value);
							break;
						case 6:
							if(value.isEmpty()) {
								entity.setBranch("Branch is null");
//								dto.setBranch(entity.getBranch());
								emptyCheck.add("BranchCode Is Empty");
							}else
							entity.setBranch(value);
							break;
						case 7:
							if(value.isEmpty()) {
								entity.setZone("Zone is Null");
								emptyCheck.add("Zone Is Null");
							}else
							entity.setZone(value);
							break;
						case 8:
							if(value.isEmpty()) {
								entity.setDistrict("District is Null");
								emptyCheck.add("District Is Null");
							}else
							entity.setDistrict(value);
							break;
						case 9:
							if(value.isEmpty()) {
								entity.setState("State is Null");
								emptyCheck.add("State Is Null");
							}else
							entity.setState(value);
							break;
						case 10:
							if(value.isEmpty()) {
								entity.setCountry("Country is Null");
								emptyCheck.add("Country Is Null");
							}else
							entity.setCountry(value);
							break;
						case 11:
							if(value.isEmpty()) {
								entity.setIfscNO("IfscNO is Null");
								emptyCheck.add("IfscNO Is Null");
							}else
							entity.setIfscNO(value);
							break;
						case 12:
							if(value.isEmpty()) {
								entity.setMicrNO("MicrNO is Null");
								emptyCheck.add("MicrNO Is Null");
							}else
							entity.setMicrNO(value);
							break;
						case 13:
							if(value.isEmpty()) {
								entity.setAccountType("AccountType is Null");
								emptyCheck.add("AccountType Is Null");
							}else
							entity.setAccountType(value);
							break;
						case 14:
							if(value.isEmpty()) {
								entity.setCardNumber("CardNumber is Null");
								emptyCheck.add("CardNumber Is Null");
							}else
							entity.setCardNumber(value);
							break;
						case 15:
							if(value.isEmpty()) {
								entity.setCardExpDate("CardExpDate is Null");
								emptyCheck.add("CardExpDate Is Null");
							}else
							entity.setCardExpDate(value);
							break;
						case 16:
							if(value.isEmpty()) {
								entity.setCardType("CardType is Null");
								emptyCheck.add("CardType Is Null");
							}else
							entity.setCardType(value);
							break;
						case 17:
							if(value.isEmpty()) {
								entity.setCardLimit("CardLimit is Null");
								emptyCheck.add("CardLimit Is Null");
							}else
							entity.setCardLimit(value);
							break;
						case 18:
							if(value.isEmpty()) {
								entity.setStatus("Status is Null");
								emptyCheck.add("setStatus Is Null");
							}else
							entity.setStatus(value);
							break;
						default:
							System.out.println("Error Occurrs...!");
							break;
						}
					}
				}
			}
			angularRepository.save(entity);
			wbook.close();
			dto.setEmail(entity.getEmail());
			dto.setUser(entity.getUser());
			dto.setPassword(entity.getPassword());
			dto.setAccountNumber(entity.getAccountNumber());
			dto.setAccountType(entity.getAccountType());
			dto.setBranch(entity.getBranch());
			dto.setCardExpDate(entity.getCardExpDate());
			dto.setCardLimit(entity.getCardLimit());
			dto.setCardNumber(entity.getCardNumber());
			dto.setCardType(entity.getCardType());
			dto.setCountry(entity.getCountry());
			dto.setCreatedBy(entity.getCreatedBy());
			dto.setCreatedDate(entity.getCreatedDate());
			dto.setDistrict(entity.getDistrict());
			dto.setIfscNO(entity.getIfscNO());
			dto.setMicrNO(entity.getMicrNO());
			dto.setState(entity.getState());
			dto.setStatus(entity.getStatus());
			dto.setZone(entity.getZone());
			dtolist.add(dto);
			commonDto.setStatus("success");
			
		      commonDto.setModel(emptyCheck);
		} catch (Exception e) {
			e.printStackTrace();
			commonDto.setStatus("Exception Occurs");
		}
		return commonDto;
	}
	

	@Override
	public CommonDto excel(AngularDto1 dto) {
		
		CommonDto commonDto =new CommonDto();
		try {
		     
			AngularEntity entity=angularRepository.getById(dto.getId());
			
		    HSSFWorkbook workbook = new HSSFWorkbook();
		    HSSFSheet sheet = workbook.createSheet("sheet");
		    HSSFRow rowhead = sheet.createRow((short) 0);
		    rowhead.createCell((short) 0).setCellValue("Id");
		    rowhead.createCell((short) 1).setCellValue("User");
		    rowhead.createCell((short) 2).setCellValue("Email");
		    rowhead.createCell((short) 3).setCellValue("Created By");
		    rowhead.createCell((short) 4).setCellValue("Created Date");
		  
		        HSSFRow row = sheet.createRow((short) 1);
		        row.createCell((short) 0).setCellValue(entity.getId());
		        row.createCell((short) 1).setCellValue(entity.getUser());
		        row.createCell((short) 2).setCellValue(entity.getEmail());
		        row.createCell((short) 3).setCellValue(entity.getCreatedBy());
		        row.createCell((short) 4).setCellValue(entity.getCreatedDate());
		     
		  
		    String fileLocation = "D:\\\\SingleData.xlsx";
		    FileOutputStream fileOut = new FileOutputStream(fileLocation);
		    workbook.write(fileOut);
		    fileOut.close();
		    commonDto.setStatus("Excel Downloaded");
		    }  catch (FileNotFoundException e1) {
		        e1.printStackTrace();
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		
		return commonDto;
		
	}

	@Override
	public ByteArrayInputStream load() {
		List<AngularEntity> entity=angularRepository.findAll();
//		ByteArrayInputStream in =uploadExcelFile(file);
		return null;
	}	
	
	
	public void student() {
		try {
			Student student=new Student();
			student.setEmailId("raja@gmail.com");
			student.setFirstName("raja");
			student.setLastName("k");
			student.setGuardianName("ka12");
			student.setGuardianEmail("ka@mgail.com");
			student.setGuardianMobile("8348347487");
			
//			studentRepository.save(student);
			
			Course course=new Course();
			course.setTitle("java");
			course.setCredit(6);
//			courseRepository.save(course);
			
			CourseMaterial courseMaterial=new CourseMaterial();
			courseMaterial.setUrl("www.google.com");
			courseMaterial.setCourse(course);
			
//			courseMaterialRepository.save(courseMaterial);
	
			
		
			
			Course teacherCourse=new Course();
			teacherCourse.setTitle("DBA");
			teacherCourse.setCredit(6);
			
			

			Course teacherCourse1=new Course();
			teacherCourse1.setTitle("java8");
			teacherCourse1.setCredit(7);
			
			Teacher teacher=new Teacher();
			teacher.setFirstName("Raja");
			teacher.setLastName("k");
			teacher.setCourses(List.of(teacherCourse,teacherCourse1));
			
			
//			teacherRepository.save(teacher);
			
			Teacher teacher2=new Teacher();
			teacher2.setFirstName("Kavi");
			teacher2.setLastName("k");
			
			
			Course  course2=new Course();
			course2.setTitle("Springboot");
			course2.setCredit(8);
			course2.setTeacher(teacher2);
			
//			courseRepository.save(course2);
			
			
			Pageable firstPageWithThreeRecords=
					PageRequest.of(0, 3);
			
			Pageable secondPageWithTwoRecords=
					PageRequest.of(1, 2);
			
			
			List<Course> courses=courseRepository.findAll(secondPageWithTwoRecords).getContent();
			
			Integer totalElements=courseRepository.findAll(secondPageWithTwoRecords).getNumberOfElements();
			
			Integer totalPages=	courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();
		
//			System.out.println("courses : "+courses);
//			
//			System.out.println("totalElements : "+totalElements);
//			
//			System.out.println("totalPages : "+totalPages); 
			
			
			Pageable firstPageWithTenRecords=PageRequest.of(0, 10);
			
			List<Course> courses2=courseRepository.findByTitleContaining("D", firstPageWithTenRecords);
		
//			System.out.println("courses : "+courses2);
			
			
			Pageable sortByTtitle=PageRequest.of(0, 2, Sort.by("title"));
			
			
			Pageable sortByCredit=PageRequest.of(0, 2, Sort.by("credit").descending());
			
			
			Pageable sortBytitleAndCredit=PageRequest.of(0, 2,Sort.by("title").descending().and(Sort.by("credit")));
			
			
			List<Course> courses3=courseRepository.findAll(sortByCredit).getContent();
			
//			System.out.println("courses : "+courses3);
			 
			
			Teacher teacher3=new Teacher();
			teacher3.setFirstName("Lokesh");
			teacher3.setLastName("k");
			
			
			
			Student student2=new Student();
			student2.setFirstName("Abishek");
			student2.setLastName("Singh");
			student2.setEmailId("abishek@gmail.com");
			
			
			Course course3=new Course();
			course3.setTitle("AI");
			course3.setCredit(3);
			course3.setTeacher(teacher3);
			
			
			course3.addStudents(student2);
			
			courseRepository.save(course3);
			
			
			GetterSetterExample getterSetterExample=new GetterSetterExample();
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public CommonDto getStudentByPage(Integer page) {
		CommonDto commonDto=new CommonDto();
		try {
			Integer  pageSize=	 Optional.ofNullable(page).orElseThrow(() ->new  PageException("Page must not be Empty")) ;
			Pageable pageable=PageRequest.of(pageSize, 2);
			List<Course>  course= courseRepository.findAll(pageable).getContent();
			
			if (course.isEmpty()) {
				commonDto.setMessage(Optional.empty().toString());
			} else {
				commonDto.setResponseDto(course);
  		
			}
			
		        	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commonDto;
	}

	@Override
	public CommonDto getCourse(Course course) {
		CommonDto commonDto=new CommonDto();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = createQuery.from(Course.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		createQuery.select(root).where(predicates.toArray(new Predicate[] {}));
		
		if (course.getCourseId() != null) {
				Predicate courseIdPredicate = criteriaBuilder.lower(root.get("courseId")).in(course.getCourseId());
				predicates.add(courseIdPredicate);
		}
		if (course.getCredit() != null) {
			Predicate creditPredicate = criteriaBuilder.lower(root.get("credit")).in(course.getCredit());
			predicates.add(creditPredicate);
		}
		if (course.getTitle() != null) {
			Predicate titlePredicate=criteriaBuilder.lower(root.get("title")).in(course.getTitle());
			predicates.add(titlePredicate);
		}
			createQuery.where(predicates.toArray(new Predicate[] {}));
			List<Course> courseList = entityManager.createQuery(createQuery.select(root).orderBy(criteriaBuilder.desc(root.get("courseId")))).getResultList();
		
			commonDto.setResponseDto(courseList);
			
			return commonDto;
	}

	@Override
	public CommonDto saveTeacher(Teacher teacher) {
		try {
			
			Optional<Teacher> teacher1 = Optional.ofNullable(teacher);
			if (teacher1.isPresent()) {
				teacherRepository.save(teacher);
			}
		boolean q=	teacher1.isPresent() ? 	teacherRepository.save(teacher) != null :false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public AngularDto1 jasperPDFReport(String caseNo){
		AngularDto1 responseDto = new AngularDto1();
		try {
//			ReportDto reportDto = new ReportDto();
//			VigilanceIntimationDetails vigilanceIntimationDetails = vigilanceIntimationDetailsRepo.findByCaseNo(caseNo);
//			
//			FraudDetails fraudDetails = vigilanceIntimationDetails.getFraudDetails().get(0);
//			ClosureDetails closureDetails = vigilanceIntimationDetails.getClosureDetails().get(0);
//			InvestigationFinding investigationFinding = vigilanceIntimationDetails.getInvestigationFinding().get(0);
//			ProcessReengDetails proccessReeng = vigilanceIntimationDetails.getProcessReengDetails().get(0);
//			
//			reportDto.setCaseNo(vigilanceIntimationDetails.getCaseNo());
//			reportDto.setCreatedDate(checkNull(CommonUtil.dateToStringConversion3(vigilanceIntimationDetails.getCreatedDate())));
//			reportDto.setDateOfIncident(checkNull(CommonUtil.dateToStringConversion3(vigilanceIntimationDetails.getDateOfIncident())));
//			reportDto.setReasonForDelay(vigilanceIntimationDetails.getReasonForDelay());
//			reportDto.setInvolvedBy(investigationFinding.getInvolvedBy());
//			reportDto.setTriggersOfInvestigation(investigationFinding.getTriggersOfInvestigation());
//			reportDto.setAllegation(investigationFinding.getAllegation());
//			reportDto.setInvestigatorName(investigationFinding.getInvestigatorName());
//			reportDto.setCaseBreif(investigationFinding.getCaseBreif());
//			reportDto.setWhatInvestigationRevealed(investigationFinding.getWhatInvestigationRevealed());
//			reportDto.setEvidenceToProveInvolvement(investigationFinding.getEvidenceToProveInvolvement());
//			reportDto.setFinancialLossToRs(investigationFinding.getFinancialLossToRs());
//			reportDto.setLimitation(proccessReeng.getLimitation());
//			reportDto.setRecommendationByVigilace(proccessReeng.getRecommendationByVigilace());
//			reportDto.setFunctionalHeadRecommendation(proccessReeng.getFunctionalHeadRecommendation());
//			reportDto.setSeverity(closureDetails.getSeverity());
//			reportDto.setNatureOfAction(closureDetails.getNatureOfAction());
//			reportDto.setFraudType(fraudDetails.getFraudType());
			
			
			
			List<DebitNoteDto> reportDtoList = new ArrayList<>();
//			AngularDto1 reportDto = new AngularDto1();
//			reportDto.setId(53);
//			reportDto.setName("Debit Note");
//			reportDto.setName("Debit Note");
//			reportDto.setBranch("Chennai");
//			reportDto.setCountry("India");
			
			
			DebitNoteDto debitNote=new DebitNoteDto();
			debitNote.setInwardDetailsId("INVRSA000655-SIP1");
			debitNote.setInceptionDate("1-JAN-2023");
			debitNote.setBrokerName("Salasar Services Insurance Brokers Pvt Ltd");
			debitNote.setBrokerAddress("Chennai");
			debitNote.setClientName("Mangalam Sir Cement Ltd");
			debitNote.setCedantName("Reliance General Insurance Company Limited");
			debitNote.setExpiry_Date("1-JAN-2024");
			debitNote.setRiskDescription("desc1");
			debitNote.setRiskLocationAddress("Trichy");
			debitNote.setProductName("Fire IAR");
			debitNote.setCapacityPercentage("5.00%");
			debitNote.setEndrosementNo("000");
			debitNote.setOrder("5.00% of 100% Order");
			debitNote.setMdsumInsured("16,093,680,000");
			debitNote.setBiSumInsured("1,250,000,000");
			debitNote.setPolicyPremium("19,581,773");
			debitNote.setPolicyPremiumourShare("979,089.00");
			debitNote.setSumInsuredTotal("17,343,680,000");
			debitNote.setCedantCommissionPercentage("12.50%");
			debitNote.setCdantCommission("122,386.13");
			debitNote.setCedantGstNo("Add :GST on Risk premium (C)@ (REF "+"06BZAHM6385P6Z2"+")");
			debitNote.setCdantGst("176,236.02");
			debitNote.setTotalD("1,032,938.90");
			debitNote.setBrokerCommissionPercentage("1.50%");
			debitNote.setBrokerCommission("14,686.34");
			debitNote.setBrokerGstNo("Less GST on RI Brokerage (F)*** @[GST(18%)]) GST REF: "+"09AAACH7409R1ZZ");
			debitNote.setBrokerGst("2,643.54");
			debitNote.setTdsOnBrokeragePercentage("5.00%");
			debitNote.setTdsOnBrokerage("734.32");
			debitNote.setTotalH("16,595.56");
			debitNote.setTotalI("663,871.30");
			debitNote.setPpwDate("PPW_DATE " +"15-FEB-2023");
			debitNote.setPayableByBrokerName("Amount Payable by Salasar Services Insurance Brokers Pvt Ltd");
			debitNote.setPayableByCedantName("Amount Payable by Reliance General Insurance Company Limited");
			debitNote.setPayableByBroker("487,635.28");
			debitNote.setPayableByCedant("176,236.02");
			
			
			
			reportDtoList.add(debitNote);
			
			JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jasper/Debite_Note_A498.jrxml"));
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportDtoList);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("inwardDetailsId", debitNote.getInwardDetailsId());
			parameters.put("inceptionDate", debitNote.getInceptionDate());
			
//			parameters.put("name", reportDto.getName());
//			parameters.put("branch", reportDto.getBranch());
//			parameters.put("country", reportDto.getCountry());
//			parameters.put("reasonForDelay", reportDto.getReasonForDelay());
//			parameters.put("involvedBy", reportDto.getInvolvedBy());
//			parameters.put("triggersOfInvestigation", reportDto.getTriggersOfInvestigation());
//			parameters.put("allegation", reportDto.getAllegation());
//			parameters.put("severity", reportDto.getSeverity());
//			parameters.put("fraudType", reportDto.getFraudType());
//			parameters.put("investigatorName", reportDto.getInvestigatorName());
//			parameters.put("caseBrief", reportDto.getCaseBreif());
//			parameters.put("natureOfAction", reportDto.getNatureOfAction());
//			parameters.put("whatInvestigationRevealed", reportDto.getWhatInvestigationRevealed());
//			parameters.put("evidenceToProveInvolvement", reportDto.getEvidenceToProveInvolvement());
//			parameters.put("limitation", reportDto.getLimitation());
//			parameters.put("financialLossToRs", reportDto.getFinancialLossToRs());
//			parameters.put("recommendationByVigilace", reportDto.getRecommendationByVigilace());
//			parameters.put("functionalHeadRecommendation", reportDto.getFunctionalHeadRecommendation());
//			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource );
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			String base64Format = getBase64Format(data);
			FileDownloadDto fileDownloadDto = new FileDownloadDto();
			fileDownloadDto.setFileName(debitNote.getInwardDetailsId() + " File Report.pdf");
			fileDownloadDto.setBase64(base64Format);
			fileDownloadDto.setBaos(data);
			responseDto.setResponseData(fileDownloadDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return responseDto;
	}
	
	
	private String getBase64Format(byte[] file) throws IOException {
		String base64Encoded = "";
		try {

			byte[] encodeBase64 = Base64.encodeBase64(file);
			StringUtils.newStringUtf8(Base64.encodeBase64(encodeBase64, false));
			base64Encoded = new String(encodeBase64, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64Encoded;
	}
	
}


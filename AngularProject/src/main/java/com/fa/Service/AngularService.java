package com.fa.Service;

import java.io.InputStream;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.fa.Dto.AngularDto1;
import com.fa.Dto.CommonDto;
import com.fa.Entity.Course;
import com.fa.Entity.Teacher;

public interface AngularService {

	 public CommonDto save1(AngularDto1 angularDto);

	public CommonDto getdata();

	public CommonDto edit(AngularDto1 dto);

//	public CommonDto deleteByName(AngularDto1 dto);

	public CommonDto login(AngularDto1 dto);

	public CommonDto editPage(Long id);

	public CommonDto uploadExcelFile(MultipartFile file);

	public CommonDto excel(AngularDto1 dto);

	public InputStream load();

	public void student();

	public CommonDto getStudentByPage(Integer page);

	public CommonDto getCourse(Course course);

	public CommonDto saveTeacher(Teacher teacher);

	public AngularDto1 jasperPDFReport(String caseNo);

}

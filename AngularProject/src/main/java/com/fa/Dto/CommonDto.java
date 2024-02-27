package com.fa.Dto;

import java.util.List;

import org.springframework.ui.Model;

public class CommonDto {
	
		private Object responseData;
		private String status;
		private String message;
		private List<?> responseDto;
		private List<String> Model;
		
		
		
		
		
		
		public List<String> getModel() {
			return Model;
		}
		public void setModel(List<String> model) {
			Model = model;
		}
		public Object getResponseData() {
			return responseData;
		}
		public String getStatus() {
			return status;
		}
		public String getMessage() {
			return message;
		}
		public List<?> getResponseDto() {
			return responseDto;
		}
		public void setResponseData(Object responseData) {
			this.responseData = responseData;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public void setResponseDto(List<?> responseDto) {
			this.responseDto = responseDto;
		}
		
		

}

package com.fa.Dto;

public class FileDownloadDto {
	
	private String fileName;
	
	private byte[] baos;
	
	private String base64;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBaos() {
		return baos;
	}

	public void setBaos(byte[] baos) {
		this.baos = baos;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

}

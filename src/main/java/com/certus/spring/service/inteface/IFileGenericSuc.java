package com.certus.spring.service.inteface;

import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.models.ResponseFileSuc;

public interface IFileGenericSuc {

	public ResponseFileSuc crearFile(MultipartFile fileGeneric);
	public ResponseFileSuc crearFileAPI(String fileBase64,String nombreFileExtension);
	public ResponseFileSuc eliminarFile(String fileName);
}

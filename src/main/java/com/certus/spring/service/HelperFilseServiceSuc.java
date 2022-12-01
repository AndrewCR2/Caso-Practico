package com.certus.spring.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.springframework.stereotype.Service;

import com.certus.spring.helper.responseFileGenericSuc;
import com.certus.spring.service.inteface.IHelperSuc;

@Service
public class HelperFilseServiceSuc implements IHelperSuc {
	
	@Override
	public responseFileGenericSuc procesarFile(String fileBase64) {
		responseFileGenericSuc rfg = new responseFileGenericSuc();
		
		byte [] fileContent =null;
		
		if (!fileBase64.isEmpty()) {
			try {
				fileContent = Base64.getDecoder().decode(new String(fileBase64).getBytes("UTF-8"));
				rfg.setFileBytes(fileContent);
				rfg.setEstado(true);
				rfg.setMensaje("archivo procesado");
			} catch (UnsupportedEncodingException e) {
				rfg.setEstado(false);
				rfg.setMensaje("se produjo un error al procesar");
			}
		}		
		return rfg;
	}

}

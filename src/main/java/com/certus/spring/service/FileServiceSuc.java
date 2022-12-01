package com.certus.spring.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.config.MvcConfig;
import com.certus.spring.helper.responseFileGenericSuc;
import com.certus.spring.models.ResponseFileSuc;
import com.certus.spring.service.inteface.IFileGenericSuc;
import com.certus.spring.service.inteface.IHelperSuc;

@Component
public class FileServiceSuc implements IFileGenericSuc{
	
	@Autowired
	MvcConfig config;
	
	@Autowired
	IHelperSuc helperfile;
	
	@Override
	public ResponseFileSuc crearFileAPI(String fileBase64, String nombreFileExtension) {
		
		ResponseFileSuc respuesta = new ResponseFileSuc();
		responseFileGenericSuc rfg = new responseFileGenericSuc();
		String rutaAbsoluta = "C:\\Users\\Usuario\\Documents\\temps\\Uploadsimg";
		
		Optional<Object> NewExtention = Optional.ofNullable(nombreFileExtension)
												.filter(e->e.contains("."))
												.map(ext -> ext.substring(nombreFileExtension.lastIndexOf(".") +1));
		
		String newName = UUID.randomUUID().toString() + "."+ NewExtention.get().toString();
		
		try {
			
			//byte[] fyleBytes = helperfile.procesarFile(fileBase64)
			byte[] bytesFile = null;
			rfg = helperfile.procesarFile(fileBase64);
			if (rfg.isEstado()) {
				bytesFile = rfg.getFileBytes();
				Path enlaceAGuardar = Paths.get(rutaAbsoluta+"//"+ newName);
				Files.write(enlaceAGuardar, bytesFile);	
				respuesta.setEstado(true);
				respuesta.setNombreFile(newName);
			}else {
				respuesta.setEstado(false);
				respuesta.setNombreFile(newName);
				respuesta.setMensajeError("se produjo un error al procesar el archivo");
			}
			
			
		} catch (IOException e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(newName);
			respuesta.setMensajeError(e.getStackTrace().toString());
		}
		
		
		
        return respuesta;
	}
	
	@Override
	public ResponseFileSuc crearFile(MultipartFile fileGeneric) {
		
		ResponseFileSuc respuesta = new ResponseFileSuc();
		String rutaAbsoluta = "C:\\Users\\Usuario\\Documents\\temps\\Uploadsimg";
		
		String NewExtention = StringUtils.getFilenameExtension(fileGeneric.getOriginalFilename());
		String newName = UUID.randomUUID().toString() + "."+ NewExtention;
		
		try {
			byte[] fyleBytes = fileGeneric.getBytes();
			Path enlaceAGuardar = Paths.get(rutaAbsoluta+"//"+ newName);
			Files.write(enlaceAGuardar, fyleBytes);		
			
			respuesta.setEstado(true);
			respuesta.setNombreFile(newName);
		} catch (IOException e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(fileGeneric.getOriginalFilename());
			respuesta.setMensajeError(e.getStackTrace().toString());
		}
		
		
		
        return respuesta;
	}
	
	@Override
	public ResponseFileSuc eliminarFile(String fileName) {
		
		ResponseFileSuc respuesta = new ResponseFileSuc();
		
		String rutaAbsoluta = "C:\\Users\\Usuario\\Documents\\temps\\Uploadsimg";
		Path enlaceAGuardado = Paths.get(rutaAbsoluta+"\\"+fileName);
		
		try {
			File fileEliminar = enlaceAGuardado.toFile();
			if(fileEliminar.exists()) {							
				fileEliminar.delete();
				
				respuesta.setEstado(true);
				respuesta.setNombreFile(fileName);
			}else {
				respuesta.setEstado(false);
				respuesta.setNombreFile(fileName);
				respuesta.setMensajeError("no se encontro");
			}
		} catch (Exception e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(fileName);
			respuesta.setMensajeError(e.getStackTrace().toString());
		}
		
		return respuesta;
	}

}

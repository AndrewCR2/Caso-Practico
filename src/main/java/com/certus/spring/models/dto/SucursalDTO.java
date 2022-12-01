package com.certus.spring.models.dto;

public class SucursalDTO {

	private int idSucursal;
	private String nombre;
	private String uriImage;
	private String nombreFileExtension;
	private String fileBase64;
	
	
	public String getNombreFileExtension() {
		return nombreFileExtension;
	}
	public void setNombreFileExtension(String nombreFileExtension) {
		this.nombreFileExtension = nombreFileExtension;
	}
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	public String getUriImage() {
		return uriImage;
	}
	public void setUriImage(String uriImage) {
		this.uriImage = uriImage;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

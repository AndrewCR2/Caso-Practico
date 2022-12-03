package com.certus.spring.service.inteface;

import com.certus.spring.models.Sucursal;


import com.certus.spring.models.ResponseSuc;
import com.certus.spring.models.dto.SucursalDTO;

public interface ISucursalService {

	
	public ResponseSuc<Sucursal> crearSucursal(Sucursal p);
	public ResponseSuc<Sucursal> crearSucursalAPI(SucursalDTO p);
	public ResponseSuc<Sucursal> editarSucursal(Integer ID);
	public ResponseSuc<Sucursal> eliminarSucursal(Integer ID);
	public ResponseSuc<Sucursal> listarSucursal();
}

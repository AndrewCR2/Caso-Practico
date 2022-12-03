package com.certus.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.certus.spring.models.Sucursal;
import com.certus.spring.models.dto.SucursalDTO;
import com.certus.spring.models.ResponseSuc;
import com.certus.spring.repository.SucursalDAO;
import com.certus.spring.service.inteface.ISucursalService;

@Component("servicioSucursal")
public class SucursalService implements ISucursalService {

	@Autowired
	SucursalDAO sucursalRepository;
	
	@Override
	public ResponseSuc<Sucursal> crearSucursal(Sucursal p) {
		ResponseSuc<Sucursal> response = new ResponseSuc<>(); 
		
		try {
			Sucursal sucursal = sucursalRepository.save(p);
			response.setEstado(true);
			response.setMensaje("La sucursal"+sucursal.getNombre()+"ha sidocreado correctamente");			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al crear la sucursal"+p.getNombre());
			response.setMensajeError(e.getStackTrace().toString());
		}
				
		return response;
	}
	
	@Override
    public ResponseSuc<Sucursal> editarSucursal(Integer ID) {
    	
    	ResponseSuc<Sucursal> response = new ResponseSuc<>();
    	
    	try {			
    		Optional<Sucursal> p = sucursalRepository.findById(ID);
    		response.setEstado(true);
    		response.setData(p.get());
    		
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("se produjo un error o no existe");
			response.setMensajeError(e.getStackTrace().toString());
		}
    	
		return response;
	}
	
	@Override
	public ResponseSuc<Sucursal> eliminarSucursal(Integer ID) {
    	
    	ResponseSuc<Sucursal> response = new ResponseSuc<>();
    	
    	try {			
    		Optional<Sucursal> p = sucursalRepository.findById(ID);
    		
    		sucursalRepository.deleteById(ID);
    		response.setEstado(true);
    		response.setMensaje("La sucursal"+p.get().getNombre()+" ha sido eliminado");
    		
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("se produjo un error o no existe");
			response.setMensajeError(e.getStackTrace().toString());
		}
		
		return response;
	}
	
	@Override
    public ResponseSuc<Sucursal> listarSucursal() {
    	
    	ResponseSuc<Sucursal> response = new ResponseSuc<>();
    	
    	try {
    		response.setListData((List<Sucursal>) sucursalRepository.findAll());
    		response.setEstado(true);
    		response.setMensaje("Sucursal obtenido correctamente");
			
		} catch (Exception e) {
			response.setEstado(false);
    		response.setMensaje("Error al obtner los sucursales");
			response.setMensajeError(e.getStackTrace().toString());
		}
  
    	return response;
    }
	
	@Override
	public ResponseSuc<Sucursal> crearSucursalAPI(SucursalDTO p) {
		ResponseSuc<Sucursal> response = new ResponseSuc<>(); 
		
		try {
			
			Sucursal Prj = new Sucursal(); 
			
			Prj.setIdSucursal(p.getIdSucursal());
			Prj.setNombre(p.getNombre());
			
			Sucursal personaje = sucursalRepository.save(Prj);
			response.setEstado(true);
			response.setMensaje("La sucursal"+personaje.getNombre()+"ha sidocreado correctamente");			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al crear la sucursal"+p.getNombre());
			response.setMensajeError(e.getStackTrace().toString());
		}
				
		return response;
	}
}

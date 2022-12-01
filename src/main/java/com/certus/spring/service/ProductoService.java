package com.certus.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.certus.spring.models.Producto;
import com.certus.spring.models.Response;
import com.certus.spring.repository.ProductoDAO;

@Component("servicioProducto")
public class ProductoService implements IProductoService {

	@Autowired
	ProductoDAO productoRepository;

	@Override
	public Response<Producto> crearProducto(Producto p) {
		Response<Producto> response = new Response<>();
		try {

			Producto Producto = productoRepository.save(p);
			response.setEstado(true);
			response.setMensaje("El Producto " + Producto.getNombre() + " ha sido creado correctamente");

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al crear el producto " + p.getNombre());
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

	@Override
	public Response<Producto> editarProducto(Integer ID) {
		Response<Producto> response = new Response<>();

		try {
			Optional<Producto> p = productoRepository.findById(ID);
			response.setEstado(true);
			response.setData(p.get());

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensajeError(e.getStackTrace().toString());
		}

		return response;
	}

	@Override
	public Response<Producto> eliminarProducto(Integer ID) {
		Response<Producto> response = new Response<>();

		try {
			Optional<Producto> p = productoRepository.findById(ID);

			productoRepository.deleteById(ID);
			response.setEstado(true);
			response.setMensaje("El producto " + p.get().getNombre() + " ha sido eliminado");

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al eliminar el producto");
			response.setMensajeError(e.getStackTrace().toString());
		}

		return response;
	}

	@Override
	public Response<Producto> listarProducto() {
		Response<Producto> response = new Response<>();

		try {

			response.setListData((List<Producto>) productoRepository.findAll());
			response.setEstado(true);
			response.setMensaje("Producto obtenidos correctamente");

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al obtener los Producto");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

}

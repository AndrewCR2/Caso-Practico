package com.certus.spring.service;

import com.certus.spring.models.Producto;
import com.certus.spring.models.Response;

public interface IProductoService {
	public Response<Producto> crearProducto(Producto p);

	public Response<Producto> editarProducto(Integer ID);

	public Response<Producto> eliminarProducto(Integer ID);

	public Response<Producto> listarProducto();
}

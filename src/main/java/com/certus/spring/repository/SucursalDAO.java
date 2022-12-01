package com.certus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.certus.spring.models.Sucursal;


@Repository
public interface SucursalDAO extends CrudRepository<Sucursal, Integer>{

}

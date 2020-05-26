package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Products;

public interface ProductDao extends CrudRepository<Products, Integer> {
		
	
		@Query("select p from Products p where productName like %?1%")
		public List<Products> findByproductName(String prod_name);
}

package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Products;

@RestController
public class ProductsController {

	@Autowired
	ProductDao productDao;
	@Autowired
	Products prod;

	@RequestMapping(method = RequestMethod.POST, value = "/products/save" , produces = { "application/json" } )
	public Map<Object,Object> postProducts(@RequestParam String prod_name, @RequestParam int unit_cost) {
		prod.setProductName(prod_name);
		prod.setUnitCost(unit_cost);
		Map<Object,Object> map = new HashMap<>();
		try {
		productDao.save(prod);
		}catch(Exception e) {
			map.put("success", 2);
			map.put("message" , "Product not inserted!");
			return map;
		}
		map.put("success", 1);
		map.put("message" , "Product Successfully inserted!");
		return map;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/get" )
	public Map<Object, Object> getProducts(@RequestParam String prod_name) {
		Map<Object, Object> map = new HashMap<>();
		List<Products> list = (List<Products>) productDao.findByproductName(prod_name);
		if(list.size() > 0){
			map.put("data", list);
			map.put("success" ,1);
		}else {
			map.put("success" , 2);
			map.put("message" , "Products not found!");
		}
		return map;
	}
}

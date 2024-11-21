package me.trihung.services;

import java.util.List;

import me.trihung.entity.Product;



public interface ProductServices {
	void delete(Long id);
	
	Product get(Long id);
	
	Product save(Product product);
	
	List<Product> listAll();
}

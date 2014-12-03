package turtapp.repository;

import java.util.List;

import turtapp.domain.Product;

public interface ProductDao{
	public List<Product> getProductList();
	public void saveProduct(Product prod);
	public List<Product> getProductListUsingFunction();
	public double getProductId();
	public List<Product> getProductListUsingFunction2();
}

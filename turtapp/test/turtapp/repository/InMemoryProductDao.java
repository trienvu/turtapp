package turtapp.repository;

import java.util.List;

import turtapp.domain.Product;
import turtapp.repository.ProductDao;

public class InMemoryProductDao implements ProductDao {

    private List<Product> productList;

    public InMemoryProductDao(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void saveProduct(Product prod) {
    }

	@Override
	public List<Product> getProductListUsingFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductListUsingFunction2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getProductId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
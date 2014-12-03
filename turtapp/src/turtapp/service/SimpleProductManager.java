package turtapp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import turtapp.domain.Product;
import turtapp.repository.ProductDao;

@SuppressWarnings("serial")
public class SimpleProductManager implements ProductManager {
	private Log logger = LogFactory.getLog(getClass());
	// private List<Product> products;
	private ProductDao productDao;

	@Override
	public void increasePrice(int percentage) {
		List<Product> products = productDao.getProductListUsingFunction();
		if (products != null) {
			for (Product product : products) {
				double newPrice = product.getPrice().doubleValue()
						* (100 + percentage) / 100;
				product.setPrice(newPrice);
				productDao.saveProduct(product);
			}
		}
	}

	@Override
	public List<Product> getProducts() {
		// return products;
		productDao.getProductId();
		return productDao.getProductListUsingFunction2();
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		logger.info("setProductManager");
	}
	/*
	 * public void setProducts(List<Product> products) { this.products =
	 * products; }
	 */
}

package turtapp.domain;

import turtapp.domain.Product;
import junit.framework.TestCase;

public class ProductTest extends TestCase {
	private Product mProduct;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mProduct = new Product();
	}

	public void testSetAndGetDescription() {
		String testDescription = "aDescription";
		assertNull(mProduct.getDescription());
		mProduct.setDescription(testDescription);
		assertEquals(testDescription, mProduct.getDescription());
	}

	public void testSetAndGetPrice() {
		double testPrice = 100.00;
		assertEquals(0, 0, 0);
		mProduct.setPrice(testPrice);
		assertEquals(testPrice, mProduct.getPrice(), 0);
	}
}

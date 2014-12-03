package turtapp.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import turtapp.domain.Product;

public class JdbcProductDao extends SimpleJdbcDaoSupport implements ProductDao {

	/* Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public List<Product> getProductList() {
		logger.info("Getting products!");
		List<Product> products = getSimpleJdbcTemplate().query(
				"select * from product", new ProductMapper());
		return products;
	}

	@Override
	public void saveProduct(Product prod) {
		logger.info("Saving product: " + prod.getDescription());
		int count = getSimpleJdbcTemplate()
				.update("update product set description = :description, price = :price where id = :id",
						new MapSqlParameterSource()
								.addValue("description", prod.getDescription())
								.addValue("price", prod.getPrice())
								.addValue("id", prod.getId()));
	}

	@Override
	public List<Product> getProductListUsingFunction() {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		SimpleJdbcCall procedureParametersCall = new SimpleJdbcCall(
				jdbcTemplate);
		procedureParametersCall
				.withFunctionName("pkg_product.get_products")
				.withoutProcedureColumnMetaDataAccess()
				.returningResultSet(
						"products",
						ParameterizedBeanPropertyRowMapper
								.newInstance(Product.class));

		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) procedureParametersCall
				.execute(new HashMap<String, Object>(0)).get("products");
		return products;
	}

	class ProductMapper implements ParameterizedRowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product prod = new Product();
			prod.setId(rs.getInt("id"));
			prod.setDescription(rs.getString("description"));
			prod.setPrice(rs.getDouble("price"));
			return prod;
		}

	}

	@Override
	public double getProductId() {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		SimpleJdbcCall procedureParametersCall = new SimpleJdbcCall(
				jdbcTemplate);
		procedureParametersCall
				.withFunctionName("pkg_product.get_products_id")
				.withoutProcedureColumnMetaDataAccess()
				.withReturnValue()
				.declareParameters(
						new SqlOutParameter("return", OracleTypes.NUMBER),
						new SqlParameter("id", OracleTypes.NUMBER));
	/*	Map in = new HashMap<>();
		in.put("id", 2);*/
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("id", 2);
		BigDecimal retVal = procedureParametersCall.executeFunction(
				BigDecimal.class, in);
		logger.info("+++++++++++++++++++++++++++++++++++++++ value: " + retVal.doubleValue());
		return retVal.doubleValue();
	}

	@Override
	public List<Product> getProductListUsingFunction2() {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		SimpleJdbcCall procedureParametersCall = new SimpleJdbcCall(
				jdbcTemplate);
		procedureParametersCall
				.withFunctionName("pkg_product.get_products_by_id")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlOutParameter("products", OracleTypes.CURSOR),
						new SqlParameter("id", OracleTypes.NUMBER))
				.returningResultSet(
						"products",
						ParameterizedBeanPropertyRowMapper
								.newInstance(Product.class));

		SqlParameterSource in = new MapSqlParameterSource().addValue("id", 1,
				OracleTypes.NUMBER);

		Map resultSet = procedureParametersCall.execute(in);
		List<Product> lstProducts = (List<Product>) resultSet.get("products");
		return lstProducts;
	}
}

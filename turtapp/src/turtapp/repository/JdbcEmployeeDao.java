package turtapp.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Types;

import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import turapp.util.IRSTConstrants;
import turtapp.domain.Employee;

public class JdbcEmployeeDao extends JdbcProductDao implements EmployeeDao {

	private static final Log logger = LogFactory.getLog(JdbcProductDao.class);
	private JdbcTemplate mJdbcTemplate;

	public JdbcEmployeeDao() {
		mJdbcTemplate = getJdbcTemplate();
	}

	private String clobToString(Clob clob) {
		Reader reader = null;
		try {
			reader = clob.getCharacterStream();
			BufferedReader bufferedReader = new BufferedReader(reader);
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			return stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return clob.toString();
	}	

	@Override
	public String getAppraisalRemarks(Long empId) throws IOException,
			SQLException {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
		simpleJdbcCall
				.withFunctionName(IRSTConstrants.F_EMP_GET_APPRAISAL_REMARKS)
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlOutParameter("RETURN_VALUE", OracleTypes.CLOB),
						new SqlParameter("IN_EMP_ID", OracleTypes.NUMBER))
				.compile();
		MapSqlParameterSource inParam = new MapSqlParameterSource().addValue(
				"IN_EMP_ID", empId, OracleTypes.NUMBER);
		Clob clobRetVal = simpleJdbcCall.executeFunction(Clob.class, inParam);
		return clobToString(clobRetVal);
	}

	@Override
	public String createAppraisalRemarks(long empId, String remarks)
			throws SQLException {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(mJdbcTemplate);
		simpleJdbcCall
				.withFunctionName(IRSTConstrants.F_EMP_CREATE_APPRAISAL_REMARKS)
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("IN_EMP_ID", OracleTypes.VARCHAR),
						new SqlParameter("IN_APPR_COMMENTS", OracleTypes.CLOB))
				.compile();
		MapSqlParameterSource inParam = new MapSqlParameterSource().addValue(
				"IN_EMP_ID", empId, OracleTypes.VARCHAR).addValue(
				"IN_APPR_COMMENTS", remarks, OracleTypes.CLOB);
		String retVal = simpleJdbcCall.executeFunction(String.class, inParam);
		return retVal;
	}

	@Override
	public String createEmployee(Employee emp) {
		SimpleJdbcCall simpleCall = new SimpleJdbcCall(mJdbcTemplate);

		simpleCall
				.withFunctionName(IRSTConstrants.F_EMP_CREATE_EMPLOYEE)
				.withReturnValue()
				.declareParameters(new SqlParameter("IN_EMP_ID", Types.NUMERIC))
				.declareParameters(
						new SqlParameter("IN_EMP_NAME", Types.VARCHAR))
				.declareParameters(new SqlParameter("IN_EMP_DOB", Types.DATE))
				.declareParameters(new SqlParameter("IN_EMP_DOJ", Types.DATE))
				.declareParameters(new SqlParameter("IN_SALARY", Types.FLOAT));

		MapSqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("IN_EMP_ID", emp.getEmpId(), Types.NUMERIC)
				.addValue("IN_EMP_NAME", emp.getEmpName(), Types.VARCHAR)
				.addValue("IN_EMP_DOB", emp.getDob(), Types.DATE)
				.addValue("IN_EMP_DOJ", emp.getDoj(), Types.DATE)
				.addValue("IN_SALARY", emp.getSalary(), Types.FLOAT);

		simpleCall.compile();

		String retVal = simpleCall.executeFunction(String.class, paramMap);

		return retVal;
	}

	@Override
	public String deleteEmployee(Employee emp) {
		SimpleJdbcCall simpleCall = new SimpleJdbcCall(getDataSource());

		simpleCall
				.withFunctionName(IRSTConstrants.F_EMP_DELETE_EMPLOYEE)
				.withReturnValue()
				.declareParameters(new SqlParameter("IN_EMP_ID", Types.VARCHAR));

		MapSqlParameterSource paramMap = new MapSqlParameterSource().addValue(
				"IN_EMP_ID", emp.getEmpId(), Types.VARCHAR);

		simpleCall.compile();

		String retVal = simpleCall.executeFunction(String.class, paramMap);

		return retVal;
	}

	@Override
	public String updateEmployee(long empId, Employee emp) {
		SimpleJdbcCall simpleCall = new SimpleJdbcCall(getDataSource());

		simpleCall
				.withFunctionName(IRSTConstrants.F_EMP_UPDATE_EMPLOYEE)
				.withReturnValue()
				.declareParameters(new SqlParameter("IN_EMP_ID", Types.NUMERIC))
				.declareParameters(
						new SqlParameter("IN_EMP_NAME", Types.VARCHAR))
				.declareParameters(new SqlParameter("IN_EMP_DOB", Types.DATE))
				.declareParameters(new SqlParameter("IN_EMP_DOJ", Types.DATE))
				.declareParameters(new SqlParameter("IN_SALARY", Types.FLOAT));

		MapSqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("IN_EMP_ID", emp.getEmpId(), Types.NUMERIC)
				.addValue("IN_EMP_NAME", emp.getEmpName(), Types.VARCHAR)
				.addValue("IN_EMP_DOB", emp.getDob(), Types.DATE)
				.addValue("IN_EMP_DOJ", emp.getDoj(), Types.DATE)
				.addValue("IN_SALARY", emp.getSalary(), Types.FLOAT);

		simpleCall.compile();

		String retVal = simpleCall.executeFunction(String.class, paramMap);

		return retVal;
	}

}

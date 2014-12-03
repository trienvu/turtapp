package turtapp.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import turtapp.domain.Employee;
import turtapp.repository.EmployeeDao;

public class SimpleEmployeeManager implements EmployeeManager {
	private EmployeeDao mEmployeeDao;

	public EmployeeDao getmEmployeeDao() {
		return mEmployeeDao;
	}

	@Autowired
	@Required
	public void setmEmployeeDao(EmployeeDao mEmployeeDao) {
		this.mEmployeeDao = mEmployeeDao;
	}

	@Override
	public String getAppraisalRemarks(long empId) throws IOException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createAppraisalRemarks(long empId, String remarks)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateEmployee(long empId, Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

}

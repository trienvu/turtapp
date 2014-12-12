package turtapp.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import turtapp.domain.Employee;
import turtapp.repository.EmployeeDao;

public class SimpleEmployeeManager implements EmployeeManager {
	private EmployeeDao employeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	@Autowired
	@Required
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public String getAppraisalRemarks(long empId) throws IOException,
			SQLException {
		return employeeDao.getAppraisalRemarks(empId);
	}

	@Override
	public String createAppraisalRemarks(long empId, String remarks)
			throws SQLException {
		return employeeDao.createAppraisalRemarks(empId, remarks);
	}

	@Override
	public String createEmployee(Employee emp) {
		return employeeDao.createEmployee(emp);
	}

	@Override
	public String deleteEmployee(Employee emp) {
		return employeeDao.deleteEmployee(emp);
	}

	@Override
	public String updateEmployee(long empId, Employee emp) {
		return employeeDao.updateEmployee(empId, emp);
	}

}

package turtapp.repository;

import java.io.IOException;
import java.sql.SQLException;

import turtapp.domain.Employee;

public interface EmployeeDao {
	String getAppraisalRemarks(Long empId) throws IOException,
			SQLException;

	String createAppraisalRemarks(long empId, String remarks)
			throws SQLException;

	String createEmployee(Employee emp);

	String deleteEmployee(Employee emp);

	String updateEmployee(long empId, Employee emp);
}

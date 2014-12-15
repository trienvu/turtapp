package turtapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import turtapp.service.EmployeeManager;

@Controller
@RequestMapping("/listEmployee.htm")
public class EmployeeController {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private EmployeeManager employeeManager;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		Map<String, Object> myModel = new HashMap<String, Object>();
		try {
			myModel.put("appraisalRemarks",
					employeeManager.getAppraisalRemarks(1L));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		// myModel.put("employees", this.productManager.getProducts());
		
		return new ModelAndView("listEmployee", "employeeModel", myModel);
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

}
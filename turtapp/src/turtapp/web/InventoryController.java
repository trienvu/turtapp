package turtapp.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import turtapp.domain.Host;
import turtapp.service.ProductManager;

@Controller
@RequestMapping("/hello.htm")
public class InventoryController {
	protected final Log logger = LogFactory.getLog(getClass());
	private ProductManager productManager;

	private String port;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Autowired
	private Host host;

	public Host getHost() {
		return host;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		String now = (new Date()).toString();
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);
		myModel.put("products", this.productManager.getProducts());

		return new ModelAndView("hello", "model", myModel);
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
		logger.info("setProductManager");
	}

}
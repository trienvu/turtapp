package turtapp.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import turtapp.service.PriceIncrease;
import turtapp.service.PriceIncreaseValidator;
import turtapp.service.ProductManager;

/*
 * By annotation the "createPriceIncrease" method with @ModelAttribute, you're telling spring how to initially 
 * populate the "priceIncrease" model value. The @SessionAttributes tells spring to automatically store the 
 * "priceIncrease" object in session after each request. Finally the "@ModelAttribute" on the method parameter
 *  for the "post" and "get" methods tells spring to find a model attribute named "priceIncrease". 
 *  It will know it's a session attribute, and find it there if it can, otherwise, 
 *  it will create it using the "createPriceIncrease" method.
 * */
@Controller
@SessionAttributes({"priceIncrease" })
@RequestMapping("/priceincrease.htm")
public class PriceIncreaseFormController3_0 {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	private ProductManager productManager;
	@Autowired
	private PriceIncreaseValidator priceIncreaseValidator;

	//formBackingObject(HttpServletRequest request)
	@ModelAttribute("priceIncrease")
	public PriceIncrease createPriceIncrease() {
		PriceIncrease priceIncrease = new PriceIncrease();
		priceIncrease.setPercentage(20);
		logger.info("First log");
		return priceIncrease;
	}	

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("priceIncrease") PriceIncrease priceIncrease,
			BindingResult result) {

		int increase = priceIncrease.getPercentage();
		priceIncreaseValidator = new PriceIncreaseValidator();
		priceIncreaseValidator.validate(priceIncrease, result);

		if (result.hasErrors())
			return "/priceincrease";

		// Validator has succeeded.
		// Perform necessary actions and return to success page.
		logger.info("Increasing prices by " + increase + "%.");
		productManager.increasePrice(increase);

		return "redirect:/hello.htm";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(ModelMap model) {
		// Perform and Model / Form initialization
		Map<Integer, String> priority = new LinkedHashMap<Integer, String>();
		priority.put(1, "Low");
		priority.put(2, "Normal");
		priority.put(3, "High");
		model.addAttribute("priorityList", priority);

		return "/priceincrease";
	}

	// Other getters and setters an needed.
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public ProductManager getProductManager() {
		return productManager;
	}
}
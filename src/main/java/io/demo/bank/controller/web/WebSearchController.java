package io.demo.bank.controller.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.demo.bank.model.AtmLocation;
import io.demo.bank.service.SearchService;
import io.demo.bank.util.Constants;
import io.demo.bank.util.Messages;

@Controller
@RequestMapping(Constants.URI_SEARCH)
public class WebSearchController extends WebCommonController {
	
	@Autowired
	private SearchService searchService;
	
	@PostMapping(Constants.URI_SEARCH_ATM)
	public String searchATMs(Principal principal, Model model, 
							 @ModelAttribute(MODEL_SEARCH_ZIPCODE) String zipcode) {
		
		// Set Display Defaults
		setDisplayDefaults(principal, model);
		
		List<AtmLocation> locations = searchService.searchATMLocations(zipcode);
		
		if (locations.isEmpty()) {
			model.addAttribute(MODEL_ATT_SUCCESS_MSG, Messages.ATM_SEARCH_EMPTY);
		}
		
		model.addAttribute(MODEL_ATT_ATM_LIST, locations);
		
		return Constants.VIEW_SEARCH;
	}

}

package DrSport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import DrSport.service.impl.CommomDataService;
import DrSport.entities.User;


@Controller
public class ContactController extends CommomController {

	@Autowired
	CommomDataService commomDataService;

	@GetMapping(value = "/contact")
	public String contact(Model model, User user) {

		commomDataService.commonData(model, user);
	return "web/contact";
	}

	@GetMapping(value = "/chinh-sach")
	public String chinhSach() {
		return "web/doi-hang";
	}

}

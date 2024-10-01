package DrSport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import DrSport.entities.CartItem;
import DrSport.entities.User;
import DrSport.repository.FavoriteRepository;
import DrSport.repository.ProductRepository;
import DrSport.service.ShoppingCartService;
import java.util.Collection;
import java.util.List;



@Service
public class CommomDataService {
	
	@Autowired
	FavoriteRepository favoriteRepository;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	TemplateEngine templateEngine;

	public void commonData(Model model, User user) {
		listCategoryByProductName(model);
		Integer totalSave = 0;
		// get count yêu thích
		if (user != null) {
			totalSave = favoriteRepository.selectCountSave(user.getUserId());
		}

		Integer totalCartItems = shoppingCartService.getCount();

		model.addAttribute("totalSave", totalSave);

		model.addAttribute("totalCartItems", totalCartItems);

		Collection<CartItem> cartItems = shoppingCartService.getCartItems();
		model.addAttribute("cartItems", cartItems);

	}
	
	// count product by category
	public void listCategoryByProductName(Model model) {

		List<Object[]> coutnProductByCategory = productRepository.listCategoryByProductName();
		model.addAttribute("coutnProductByCategory", coutnProductByCategory);
	}


}

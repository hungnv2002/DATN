package DrSport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import DrSport.service.impl.CommomDataService;
import  DrSport.entities.Product;
import  DrSport.entities.User;
import  DrSport.repository.ProductRepository;

import java.util.List;


@Controller
public class ProductDetailController extends CommomController{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CommomDataService commomDataService;

	@GetMapping(value = "productDetail")
	public String productDetail(@RequestParam("id") Long id, Model model, User user) {

		Product product = productRepository.findById(id).orElse(null);
		model.addAttribute("product", product);

		commomDataService.commonData(model, user);
		listProductByCategory10(model, product.getCategory().getCategoryId());

		return "web/productDetail";
	}
	
	// Gợi ý top 10 sản phẩm cùng loại
	public void listProductByCategory10(Model model, Long categoryId) {
		List<Product> products = productRepository.listProductByCategory10(categoryId);
		model.addAttribute("productByCategory", products);
	}

}

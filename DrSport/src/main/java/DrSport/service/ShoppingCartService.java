package DrSport.service;

import org.springframework.stereotype.Service;
import  DrSport.entities.CartItem;
import  DrSport.entities.Product;

import java.util.Collection;


@Service
public interface ShoppingCartService {

	int getCount();

	double getAmount();

	void clear();

	Collection<CartItem> getCartItems();

	void remove(CartItem item);

	void add(CartItem item);
	
	public void updateQuantity(Long productId, int newQuantity);

	void remove(Product product);

}

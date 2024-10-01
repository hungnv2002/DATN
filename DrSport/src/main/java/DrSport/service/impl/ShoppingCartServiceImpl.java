package DrSport.service.impl;

import org.springframework.stereotype.Service;
import  DrSport.entities.CartItem;
import  DrSport.entities.Product;
import  DrSport.service.ShoppingCartService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private Map<Long, CartItem> map = new HashMap<Long, CartItem>(); // <Long, CartItem>

	@Override
	public void add(CartItem item) {
		CartItem existedItem = map.get(item.getId());

		if (existedItem != null) {
			existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
			existedItem.setTotalPrice(item.getTotalPrice() + existedItem.getUnitPrice() * existedItem.getQuantity());
		} else {
			map.put(item.getId(), item);
		}
	}

	@Override
	public void remove(CartItem item) {

		map.remove(item.getId());

	}

	@Override
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public double getAmount() {
		return map.values().stream().mapToDouble(item -> item.getQuantity() * item.getUnitPrice()).sum();
	}

	@Override
	public int getCount() {
		if (map.isEmpty()) {
			return 0;
		}

		return map.values().size();
	}

	@Override
	public void remove(Product product) {

	}

	@Override
	public void updateQuantity(Long productId, int newQuantity) {
		  CartItem item = map.get(productId);
		    if (item != null) {
		        item.setQuantity(newQuantity);
		        item.setTotalPrice(item.getUnitPrice() * newQuantity);
		    }
	}
}	

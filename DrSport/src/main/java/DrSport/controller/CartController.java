package DrSport.controller;

import DrSport.service.impl.VNPayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import DrSport.service.impl.CommomDataService;
import DrSport.entities.*;
import DrSport.repository.OrderDetailRepository;
import DrSport.repository.OrderRepository;
import DrSport.service.ShoppingCartService;
import DrSport.util.Utils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Controller
public class CartController extends CommomController {

	@Autowired
	HttpSession session;

	@Autowired
	CommomDataService commomDataService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	private VNPayService vnPayService; // Use VNPayService instead of PaypalService

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	public Order orderFinal = new Order();

	public static final String URL_VNPAY_SUCCESS = "pay/vnpay/success";
	public static final String URL_VNPAY_CANCEL = "pay/vnpay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());

	// add cartItem
	@GetMapping(value = "/addToCart")
	public String add(@RequestParam("productId") Long productId, HttpServletRequest request, Model model) {

		Product product = productRepository.findById(productId).orElse(null);

		session = request.getSession();
		Collection<CartItem> cartItems = shoppingCartService.getCartItems();
		if (product != null) {
			CartItem item = new CartItem();
			BeanUtils.copyProperties(product, item);
			item.setQuantity(1);
			item.setProduct(product);
			item.setId(productId);
			shoppingCartService.add(item);
		}
		session.setAttribute("cartItems", cartItems);
		model.addAttribute("totalCartItems", shoppingCartService.getCount());

		return "redirect:/products";
	}

	@GetMapping(value = "/remove/{id}")
	public String remove(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
		Collection<CartItem> cartItems = shoppingCartService.getCartItems();
		session = request.getSession();

		// Tìm và xóa CartItem từ id
		Optional<CartItem> optionalCartItem = cartItems.stream()
				.filter(item -> item.getId().equals(id))
				.findFirst();
		if (optionalCartItem.isPresent()) {
			CartItem itemToRemove = optionalCartItem.get();

			// Xóa CartItem khỏi giỏ hàng
			shoppingCartService.remove(itemToRemove);

			// Cập nhật danh sách cartItems trong session
			cartItems.remove(itemToRemove);
		}

		model.addAttribute("totalCartItems", shoppingCartService.getCount());
		return "redirect:/checkout";
	}

	// show check out
	@GetMapping(value = "/checkout")
	public String checkOut(Model model, User user) {

		Order order = new Order();
		model.addAttribute("order", order);

		Collection<CartItem> cartItems = shoppingCartService.getCartItems();
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", shoppingCartService.getAmount());
		model.addAttribute("NoOfItems", shoppingCartService.getCount());
		double totalPrice = 0;
		for (CartItem cartItem : cartItems) {
			double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
			totalPrice += price - (price * cartItem.getProduct().getDiscount() / 100);
		}

		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("totalCartItems", shoppingCartService.getCount());
		commomDataService.commonData(model, user);

		return "web/shoppingCart_checkout";
	}

	// submit checkout
	@PostMapping(value = "/checkout")
	@Transactional
	public String checkedOut(Model model, Order order, HttpServletRequest request, User user)
			throws MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException {

		String checkOut = request.getParameter("checkOut");

		Collection<CartItem> cartItems = shoppingCartService.getCartItems();

		double totalPrice = 0;
		for (CartItem cartItem : cartItems) {
			double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
			totalPrice += price - (price * cartItem.getProduct().getDiscount() / 100);
		}

		BeanUtils.copyProperties(order, orderFinal);
		if (StringUtils.equals(checkOut, "vnpay")) {

			String cancelUrl = Utils.getBaseURL(request) + "/" + URL_VNPAY_CANCEL;
			String successUrl = Utils.getBaseURL(request) + "/" + URL_VNPAY_SUCCESS;
			try {
				String paymentUrl = vnPayService.createPaymentUrl(totalPrice, "Order Payment", successUrl, request);
				return "redirect:" + paymentUrl;
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				log.error(e.getMessage());
			}
		}

		session = request.getSession();
		Date date = new Date();
		order.setOrderDate(date);
		order.setStatus(0);
		order.setAmount(totalPrice);
		order.setUser(user);

		orderRepository.save(order);

		for (CartItem cartItem : cartItems) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(cartItem.getQuantity());
			orderDetail.setOrder(order);
			orderDetail.setProduct(cartItem.getProduct());
			double unitPrice = cartItem.getProduct().getPrice();
			orderDetail.setPrice(unitPrice);
			orderDetailRepository.save(orderDetail);
		}



		shoppingCartService.clear();
		session.removeAttribute("cartItems");
		model.addAttribute("orderId", order.getOrderId());

		return "redirect:/checkout_success";
	}

	// vnpay success
	@GetMapping(URL_VNPAY_SUCCESS)
	public String successPay(HttpServletRequest request, Model model) throws MessagingException {
		Collection<CartItem> cartItems = shoppingCartService.getCartItems();
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", shoppingCartService.getAmount());

		double totalPrice = 0;
		for (CartItem cartItem : cartItems) {
			double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
			totalPrice += price - (price * cartItem.getProduct().getDiscount() / 100);
		}
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("totalCartItems", shoppingCartService.getCount());

		// Payment success logic
		// You might need to handle the successful payment and update the order status

		// For example, if you are using a specific parameter to verify the payment status, you can process it here

		// Assuming that you are verifying payment via query parameters or session data

		session = request.getSession();
		Date date = new Date();
		orderFinal.setOrderDate(date);
		orderFinal.setStatus(0);
		orderFinal.setAmount(totalPrice);
		orderRepository.save(orderFinal);

		for (CartItem cartItem : cartItems) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(cartItem.getQuantity());
			orderDetail.setOrder(orderFinal);
			orderDetail.setProduct(cartItem.getProduct());
			double unitPrice = cartItem.getProduct().getPrice();
			orderDetail.setPrice(unitPrice);
			orderDetailRepository.save(orderDetail);
		}

		shoppingCartService.clear();
		session.removeAttribute("cartItems");
		model.addAttribute("orderId", orderFinal.getOrderId());
		orderFinal = new Order();
		return "redirect:/checkout_vnpay_success";
	}

	// done checkout ship cod
	@GetMapping(value = "/checkout_success")
	public String checkoutSuccess(Model model, User user) {
		commomDataService.commonData(model, user);

		return "web/checkout_success";
	}

	// done checkout vnpay
	@GetMapping(value = "/checkout_vnpay_success")
	public String vnpaySuccess(Model model, User user) {
		commomDataService.commonData(model, user);

		return "web/checkout_vnpay_success";
	}

	@PutMapping(value = "/updateQuantity", params = { "productId", "quantity" })
	public String updateQ(ModelMap model, HttpSession session, @RequestParam("productId") Long id,
						  @RequestParam("quantity") int qty) {
		shoppingCartService.updateQuantity(id, qty);

		return "web/shoppingCart_checkout";
	}
}

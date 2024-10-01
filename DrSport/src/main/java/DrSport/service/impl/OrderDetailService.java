package DrSport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  DrSport.entities.Order;
import  DrSport.repository.OrderRepository;

import java.util.List;


@Service
public class OrderDetailService implements DrSport.service.OrderDetailService {

	@Autowired
	OrderRepository repo;

	public List<Order> listAll() {
		return (List<Order>) repo.findAll();
	}

}

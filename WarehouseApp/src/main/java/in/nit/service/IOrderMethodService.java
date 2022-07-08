package in.nit.service;

import java.util.List;
import java.util.Optional;

import in.nit.model.OrderMethod;

public interface IOrderMethodService {
	Integer saveOrderMethod(OrderMethod om);
	void updateOrderMethod(OrderMethod om);
	
	void deleteOrderMethod(Integer id);
	Optional<OrderMethod> getOneOrderMethod(Integer id);
	
	List<OrderMethod> getAllOrderMethod();
	boolean isOrderMethodExist(Integer id);
}

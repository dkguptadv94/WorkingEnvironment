package in.nit.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.model.OrderMethod;
import in.nit.repository.OrderMethodRepository;
import in.nit.service.IOrderMethodService;
@Service
public class OrderMethodServiceImpl implements IOrderMethodService {
@Autowired
	private OrderMethodRepository repo;
	
	@Transactional
	public Integer saveOrderMethod(OrderMethod om) {
		
		return repo.save(om).getId();
	}
	
	@Transactional
	public void updateOrderMethod(OrderMethod om) {
		repo.save(om);
	}

	@Transactional
	public void deleteOrderMethod(Integer id) {
		repo.deleteById(id);
	}

	@Transactional(readOnly=true)
	public Optional<OrderMethod> getOneOrderMethod(Integer id) {
		
		return repo.findById(id);
	}

	@Transactional(readOnly=true)
	public List<OrderMethod> getAllOrderMethod() {
		return repo.findAll();
	}

	@Transactional(readOnly=true)
	public boolean isOrderMethodExist(Integer id) {
		return repo.existsById(id);
	}

}

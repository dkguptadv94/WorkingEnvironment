package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.model.ShipmentType;
import in.nit.repository.ShipmentTypeRepository;
import in.nit.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {
	
	@Autowired
	private ShipmentTypeRepository repo;

	@Transactional
	public Integer saveShipmentType(ShipmentType st) {
		Integer id=repo.save(st).getId();
		return id;
	}

	@Transactional
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);
	}

	@Transactional
	public void deleteShipmentType(Integer id) {
		repo.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Optional<ShipmentType> getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ShipmentType> getAllShipmentTypes() {
		List<ShipmentType> list=repo.findAll();
		return list;
	}

	@Transactional(readOnly = true)
	public boolean isShipmentTypeExist(Integer id) {
		boolean flag=repo.existsById(id);
		return flag;
	}

}

package in.nit.service;

import java.util.List;
import java.util.Optional;

import in.nit.model.ShipmentType;

public interface IShipmentTypeService {
	
	Integer saveShipmentType(ShipmentType st);
	void updateShipmentType(ShipmentType st);
	void deleteShipmentType(Integer id);
	Optional<ShipmentType>getOneShipmentType(Integer id);
	List<ShipmentType> getAllShipmentTypes();
	boolean isShipmentTypeExist(Integer id);
}

package in.nit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nit.model.ShipmentType;
import in.nit.service.IShipmentTypeService;

@Controller
@RequestMapping("/shipmenttype")
public class ShipmentTypeController {
	@Autowired
	private IShipmentTypeService service;
	
	//1.Display Register page.
	@GetMapping("/register")
	public String showRegister(Model model){
		//form backing object
		model.addAttribute("shipmentType",new ShipmentType());
		return "ShipmentTypeRegister";
	}
	
	//2. save : on click submit
	@PostMapping("/save")
	public String save(@ModelAttribute ShipmentType shipmentType,Model model) {
		//perform save operation
		Integer id=service.saveShipmentType(shipmentType);
		//construct one msg
		String msg="ShipmentType "+shipmentType.getShipmentCode()+" saved  successfully";
		//send message to UI
		model.addAttribute("message",msg);
		//Goto page
		return "ShipmentTypeRegister";
	}
	
	//3. Display data
	@GetMapping("/all")	
	public String showAll(Model model) {
			List<ShipmentType> list=service.getAllShipmentTypes();
			model.addAttribute("list",list);
			return "ShipmentTypeData";
		}
	 
	//4. Remove one by id
	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable Integer id,Model model) {
		String msg=null;
		
		if(service.isShipmentTypeExist(id)) { //if row exist
			service.deleteShipmentType(id);
			//msg="ShipmentType "+id+" Deleted";
			msg="ShipmentType Deleted";
			
		//fetch other row and display
		}//end if
		else {
			 //msg="ShipmentType "+id+" Not exist";
			msg="ShipmentType Not exist";
		}//end else
		model.addAttribute("message", msg);
		//show other rows
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		
		return "ShipmentTypeData";
	}
	/**
	 * On click edit hyperLink at UI
	 * read one PathVariable and fetch data from
	 * service, if exist send to edit page
	 * else redirect to data page
	 */
	//5. Show edit page with data
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id,Model model) {
		Optional<ShipmentType> opt=service.getOneShipmentType(id);
		String page=null;
		if(opt.isPresent()) {
			ShipmentType st=opt.get();
			//form backing object with data
			model.addAttribute("shipmentType", st);
			page="ShipmentTypeEdit";
		}//if end
		else {
			page="redirect:../all";
		}//end else
		return page;
	}
	
	/**
	 * On click updated button , read data and perform update operation
	 * send back to data page with success message.
	 */
	//6. Update on click update
	@PostMapping("/update")
	public String update(@ModelAttribute ShipmentType shipmentType, Model model) {
		service.updateShipmentType(shipmentType);
		String msg="ShipmentType "+shipmentType.getShipmentCode()+" Update";
		model.addAttribute("message", msg);
		
		//new data from DB ,show other rows

		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		return "ShipmentTypeData";
	}
}

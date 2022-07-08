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

import in.nit.model.OrderMethod;
import in.nit.service.IOrderMethodService;

@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {
	@Autowired
	private IOrderMethodService service;
	
	
	//1. Show Reg page
	@GetMapping("/register")
	public String showReg(Model model) {
		model.addAttribute("orderMethod", new OrderMethod());
		return "OrderMethodRegister";
	}
	//2. save data
	@PostMapping("/save")
	public String save(@ModelAttribute OrderMethod
			orderMethod, Model model) {
		Integer id=service.saveOrderMethod(orderMethod);
		String message="Order Method "+id+" saved";
		model.addAttribute("message", message);
			//clear form	
		model.addAttribute("orderMethod", new OrderMethod());
		return "OrderMethodRegister";
	}
	//3. show all
	@GetMapping("/all")
	public String showAll(Model model) {
		List<OrderMethod> list = service.getAllOrderMethod();
		model.addAttribute("list",list);
		return "OrderMethodData";
	}
	//4. delete
	 @GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id,Model model) {
		String message=null;
		if(service.isOrderMethodExist(id)) {
			service.deleteOrderMethod(id);
			message="Order Method "+id+" Deleted";
		}
		else {
			message="Order Method "+id+" Not exist";
		}
		model.addAttribute("message", message);
		List<OrderMethod> list = service.getAllOrderMethod();
		model.addAttribute("list",list);
		return "OrderMethodData";
	}
	//5. edit page
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id, Model model) {
		Optional<OrderMethod> opt = service.getOneOrderMethod(id);
		String page=null;
		if(opt.isPresent()) {
			model.addAttribute("orderMethod",opt.get());
			page="OrderMethodEdit";
		}else {
			page="redirect:../all";
		}//end else
		return page;
	}
	
	//6. do update
	@PostMapping("/update")
	public String update(@ModelAttribute OrderMethod orderMethod,Model model) {
		service.updateOrderMethod(orderMethod);
		model.addAttribute("message", "Order Method "+orderMethod.getId()+"Updated!!");
		model.addAttribute("list",service.getAllOrderMethod());
		return "OrderMethodData";
	}
	
	//7. show one
	@GetMapping("/view/{id}")
	public String showView(@PathVariable Integer id, Model model) {
		String page=null;
		Optional<OrderMethod> opt= service.getOneOrderMethod(id);
		if(opt.isPresent()) {
			OrderMethod om=opt.get();
			model.addAttribute("ob",om);
			page="OrderMethodView";
		}
		return page;
	}
}

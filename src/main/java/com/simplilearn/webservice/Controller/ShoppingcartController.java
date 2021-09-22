package com.simplilearn.webservice.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.webservice.bean.Product;
import com.simplilearn.webservice.service.OrderService;
import com.simplilearn.webservice.service.ProductService;
import com.simplilearn.webservice.utility.Items;




@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingcartController {
	
	@Autowired
	private ProductService productService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ordernow", method = RequestMethod.GET)
	public ModelAndView ordernow(HttpServletRequest req, ModelMap mm, HttpSession session) {
		int id = Integer.parseInt(req.getParameter("pid"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		Product p = this.productService.getProduct(id);
		p.setProductQuantity(quantity);
		if (session.getAttribute("cart") == null) {
			List<Items> cart = new ArrayList<Items>();
			cart.add(new Items(p, quantity));
			session.setAttribute("cart", cart);
		} else {
			List<Items> cart = (List<Items>) session.getAttribute("cart");
			int index = isExisting(id, session);
			if (index == -1) {
				cart.add(new Items(p, quantity));
			}
			else {
				int qty = cart.get(index).getQuantity() + quantity;
				cart.get(index).setQuantity(qty);
			}
			System.out.println("New Cart "+cart);
			session.setAttribute("cart", cart);
		}
		ModelAndView mav = new ModelAndView("redirect:/displayAllProductToCustomer.html");
		return mav; 
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable(value = "id") int id, HttpSession session) {
		List<Items> cart = (List<Items>) session.getAttribute("cart");
		System.out.println("I Came Here");
		int index = isExisting(id, session);
		cart.remove(index);
		session.setAttribute("cart", cart);
		ModelAndView mav = new ModelAndView("redirect:/cart.html");
		return mav;
	}

	@SuppressWarnings("unchecked")
	private int isExisting(int id, HttpSession session) {

		List<Items> cart = (List<Items>) session.getAttribute("cart");

		for (int i = 0; i < cart.size(); i++)

			if (cart.get(i).getProduct().getPid() == id)
				return i;

		return -1;
	}
	@RequestMapping(value = "/shopping")
	public ModelAndView shopping() {
		ModelAndView mav = new ModelAndView("redirect:/cart.html");
		return mav;
	}
	
	@Autowired
	OrderService orderService;
		
	@RequestMapping(value="placeOrder/{totalAmount}")
	@SuppressWarnings("unchecked")
	public ModelAndView placeOrder( @PathVariable("totalAmount") float totalAmount,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Items> listOfItem = (List<Items>)session.getAttribute("cart");
		String userEmail = (String)session.getAttribute("user");
		productService.reduceQuantity(listOfItem);
		String result = orderService.placeOrder(listOfItem, userEmail, totalAmount);
		System.out.println(result);
		session.setAttribute("orderMsg", result);
		session.removeAttribute("cart");
		mav.setViewName("redirect:/customerHome.html");
		return mav;
	}
}
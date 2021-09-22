package com.simplilearn.webservice.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.webservice.bean.Product;
import com.simplilearn.webservice.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "storeProductPage",method = RequestMethod.GET)
	public ModelAndView storeProductPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addProduct.html");
		return mav;
	}
	
	@RequestMapping(value = "storeProduct",method = RequestMethod.POST)
	public ModelAndView storeProductDetails(HttpServletRequest req) {
		Product product = new Product();
		product.setProductName(req.getParameter("productName"));
		product.setProductPrice(Float.parseFloat(req.getParameter("productPrice")));
		product.setProductQuantity(Integer.parseInt(req.getParameter("productQuantity")));
		product.setProductUrl(req.getParameter("productUrl"));
		ModelAndView mav = new ModelAndView();
		String result = productService.stroreProductDetails(product);
		mav.addObject("msg", result);
		mav.setViewName("addProduct.html");
		return mav;
	}
	
	@RequestMapping(value = "displayAllProduct")
	public ModelAndView getAllProductDetails(HttpSession hs) {
		ModelAndView mav = new ModelAndView();
		List<Product> allProducts = productService.getAllProduct();
		hs.setAttribute("allProduct", allProducts);
		mav.setViewName("viewAllProduct.html");
		return mav;
	}
	
	@RequestMapping(value = "displayAllProductToCustomer")
	public ModelAndView getAllProductDetailsToCustomer(HttpSession hs) {
		ModelAndView mav = new ModelAndView();
		List<Product> allProducts = productService.getAllProduct();
		hs.setAttribute("allProductToCustomer", allProducts);
		mav.setViewName("displayAllProductToCustomer.html");
		return mav;
	}
	
	@RequestMapping(value = "updateProductView",method = RequestMethod.GET)
	public ModelAndView getToAllUpdateProductDetails(HttpSession hs) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("updateProduct.html");
		return mav;
	}
	
	@RequestMapping(value = "updateSelectedProduct",method = RequestMethod.GET)
	public ModelAndView updateSelectedProduct(HttpServletRequest req,HttpSession hs) {
		int pid = Integer.parseInt(req.getParameter("pid"));
		System.out.println("Product id is "+pid);
		ModelAndView mav = new ModelAndView();
		System.out.println("Event fired...");
		Product pp = productService.getProduct(pid);
		if(pp!=null) {
			hs.setAttribute("productInfo", pp);
			hs.removeAttribute("pmsg");
		}else {
			hs.setAttribute("pmsg", "Produt not available");
			hs.removeAttribute("productInfo");
		}
		mav.setViewName("updateProduct.html");
		hs.removeAttribute("productUpdateMsg");
		return mav;
	}
	
	@RequestMapping(value="updateProdcut",method = RequestMethod.POST)
	public ModelAndView updateProduct(HttpServletRequest req,HttpSession hs) {
		Product pro = new Product();
		pro.setPid(Integer.parseInt(req.getParameter("pid")));
		pro.setProductName(req.getParameter("productName"));
		pro.setProductPrice(Float.parseFloat(req.getParameter("productPrice")));
		pro.setProductQuantity(Integer.parseInt(req.getParameter("productQuantity")));
		System.out.println(pro);
		ModelAndView mav = new ModelAndView();
		String result = productService.udpateProductDetails(pro);
		hs.setAttribute("productUpdateMsg", result);
		hs.removeAttribute("productInfo");
		mav.setViewName("updateProduct.html");
		return mav;
	}
}

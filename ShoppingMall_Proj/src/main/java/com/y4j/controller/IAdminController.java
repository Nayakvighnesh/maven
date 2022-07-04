package com.y4j.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.y4j.dto.ProductDTO;
import com.y4j.entity.Admin;
import com.y4j.entity.Category;
import com.y4j.entity.Product;
import com.y4j.service.IAdminServiceImpl;
import com.y4j.service.ICategoryServiceImpl;
import com.y4j.service.IProductServiceImpl;

   @Controller
   public class IAdminController
      {
	
	public static String uploadDir=System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
    private IAdminServiceImpl userService;
	
	@Autowired
	private ICategoryServiceImpl categoryservice;
	
	@Autowired
	private IProductServiceImpl productservice;
	
	@GetMapping("/home")
	public String homePage()
	{
		return"index";
	}
	
	//This handler mapping methods for Admin class
	@GetMapping("/admin/new")
	public String userNewForm(Model model)
	 {
		 model.addAttribute("admin",new Admin());	
		 
		 return "signup";
	 }
	
	@PostMapping("/admin/save")
	public String saveUser(Admin admin)
	  {
		userService.addAdmin(admin);
		return "redirect:/admin/loginpage";
	  }
	
	   
	
    @GetMapping("/admin/loginpage")
           
    public ModelAndView login() 
     {
    	ModelAndView mav = new ModelAndView("signin");
        mav.addObject("user", new Admin());
        return mav;
     }
 
    @PostMapping("/admin/login")
    public String login(@ModelAttribute("user") Admin user )
       {
    	
    	//here we check exsting user if it is null then it redirect again login page
    	Admin existUser = userService.login(user.getUsername(), user.getPassword());
    	
 
    	System.out.print(existUser);
    	if(Objects.nonNull(existUser)) 
    	      {	
  
    		return "redirect:/shop";
    	
    		
    	       } else 
    	                 {
    		
    		            return "redirect:/admin/loginpage";
    		
    	
    	                   }
 
          }
    
    @RequestMapping(value = {"/admin/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
     {
    	
	  
        return "redirect:/admin/loginpage";
     }
    
    
	
	
	    //Admin can manage category and product controller
	    // This handler mapping methods for Admin class
		@GetMapping("/admin/categories")
		public String listcat(Model model)
		 {
		  List<Category> listcategories =categoryservice.getAllCategory();
		  model.addAttribute("listCategories",listcategories);
		  return "categories";
		 }
		

		@GetMapping("/admin/categories/new")
		public String catNewForm(Model model)
		 {
			 model.addAttribute("category",new Category());	
			 
			 return "categoriesform";
		 }
		
		
		@PostMapping("/admin/categories/save")
		public String saveCat(Category category)
		 {
			categoryservice.addCategory(category);
			return "redirect:/admin/categories";
		 }
		
		 @RequestMapping("/admin/category/delete/{id}")
		    public String deleteCategory(@PathVariable(name = "id") int id) 
		     {
			    categoryservice.removeCategoryById(id);
		        return "redirect:/admin/categories";
		      }
		 
		 
		
		    //product section
			@GetMapping("/admin/products")
			public String getProduct(Model model)
			{
				
				model.addAttribute("products",productservice.getAllProduct());
				
				return "products";
			}

			@GetMapping("/admin/products/add")
			public String  prdAdd(Model model)
			{
				model.addAttribute("productDTO",new ProductDTO());
				model.addAttribute("categories",categoryservice.getAllCategory());
				return "productsAdd";
			}
			
			@PostMapping("/admin/products/add")
			public String  postProductAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
					@RequestParam("productImage") MultipartFile file,
					@RequestParam("imgName")String imgName) throws IOException
			{
				
				// we have to convert productdto object into  product 
				
				Product product=new Product();
				product.setId(productDTO.getId());
				product.setName(productDTO.getName());
				product.setCategory(categoryservice.getCategoryById(productDTO.getCategoryId()).get());
				product.setPrice(productDTO.getPrice());
				product.setWeight(productDTO.getWeight());
				product.setDescription(productDTO.getDescription());
				
				
				String imageUUID;
				if(!file.isEmpty())
				  {
					imageUUID=file.getOriginalFilename();
					Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
					Files.write(fileNameAndPath, file.getBytes());
				  }
				   else  { 
					    imageUUID=imgName;
				       }
				
				product.setImageName(imageUUID);
				productservice.addProduct(product);
				

				return "redirect:/admin/products";
			}
			
			@GetMapping("/admin/product/delete/{id}")
			public String deleteProduct(@PathVariable long id)
			{
				productservice.removeProductById(id);
				return "redirect:/admin/products";
				
			}
			@GetMapping("/admin/product/update/{id}")
			public String updateProduct(@PathVariable long id,Model model)
			{
				
				Product product=productservice.getProductById(id).get();
				ProductDTO productdto=new ProductDTO();
				
				productdto.setId(product.getId());
				productdto.setName(product.getName());
				productdto.setPrice(product.getPrice());
				productdto.setWeight(product.getWeight());
				productdto.setDescription(product.getDescription());
				productdto.setCategoryId(product.getCategory().getId());
				productdto.setImageName(product.getImageName());
				
				model.addAttribute("categories", categoryservice.getAllCategory());
				model.addAttribute("productDTO", productdto);
				
				return "productsAdd";
			}
	
			
			
			
			//this is final view of products by categorywise
			@GetMapping("/shop")
			public String shop(Model model)
			{
				model.addAttribute("categories",categoryservice.getAllCategory());
				model.addAttribute("products", productservice.getAllProduct());
				
				return "shop";
				}
			
			@GetMapping("/shop/category/{id}")
			public String shopByCategory(@PathVariable int id,Model model)
			{
				model.addAttribute("categories",categoryservice.getAllCategory());
				model.addAttribute("products", productservice.getAllProductsByCategoryId(id));
				return "shop";
			}
			
			@GetMapping("/shop/viewproduct/{id}")
			public String viewProduct(@PathVariable Long id,Model model)
			{
				model.addAttribute("product", productservice.getProductById(id).get());
				return "viewProduct";
			}
			
			
	
}

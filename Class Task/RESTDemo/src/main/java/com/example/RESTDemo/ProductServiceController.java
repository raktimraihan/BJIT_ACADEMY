package com.example.RESTDemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {

    private Map<String, Product> productRepo = new HashMap<>();

    ProductServiceController() {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>(productRepo.values(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
    	if(productRepo.containsKey(product.getId())) {
    		Product prod = productRepo.get(product.getId());
    		prod.setId(product.getId());
    		prod.setName(product.getName());
    		productRepo.put(prod.getId(), prod);
    		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    	}
    	
    	else return null;
    }
    
   @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
   public ResponseEntity<Object> deleteProduct(@RequestBody String str){
	   if(productRepo.containsKey(str)) {
		   Product prod = productRepo.get(str);
		   productRepo.remove(str);
		   return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	   }
	   else return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
   }

}

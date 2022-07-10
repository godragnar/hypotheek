package com.wipro.velocity.hypotheek.controller;

import java.util.List;
import java.util.Optional;

import com.wipro.velocity.hypotheek.model.User;

import com.wipro.velocity.hypotheek.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {

	@Autowired
	private UserRepository userrepo;
	
	
	//POST-http://locahhost:8989/dhl/api/registerUser
	@PostMapping("/registerUser")
    public String saveUser(@RequestBody User user){
        userrepo.save(user);
       
        return "Added Successfully";
    }
	
	//DELETE--http://locahhost:8989/dhl/api/register/delete/{id}
	@DeleteMapping("register/delete/{id}")
    public String deleteUser(@PathVariable String id){
        userrepo.deleteById(id);
       
        return "Deleted Successfully";
    }
	
	//GET--http://locahhost:8989/dhl/api/findAllUsers
	@GetMapping("/findAllUsers")
    public List<User> getUsers() {
       
        return userrepo.findAll();
    }

	//GET--http://locahhost:8989/dhl/api/findByEmail/{emailId}
	 @GetMapping("/findByEmail/{emailId}")
	    public User findUser(@PathVariable String emailId){
	    	
	    	return userrepo.findByEmailId(emailId);
	 }
	 
	 
	 //POST--//GET--http://locahhost:8989/dhl/api/loginUser
	 @PostMapping("/loginUser")
	    public Boolean loginUser(@Validated @RequestBody User user)
	    {
	        Boolean a=false;;
	        String emailId=user.getEmailId();
	        String password=user.getPassword();
	        //System.out.println(email+password);
	        User u = userrepo.findByEmailId(emailId);//.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: "));
	    //    System.out.println(d.getEmail() +d.getPassword() );
	       
	        if(emailId.equals(u.getEmailId()) && password.equals(u.getPassword()))
	                {
	        //    System.out.println(d.getEmail() +d.getPassword() );
	            a=true;
	           
	                }
	        return a;
	    }
	 
	 //PUT--http://locahhost:8989/dhl/api/update
	 
	 @PutMapping("/update")
	 public String updateUser(@RequestParam("id") String id, @RequestParam("status") boolean status) {
		 Optional<User> user = userrepo.findById(id);
		 if(user.isPresent()) {
			 user.get().setAccept(status);
			 userrepo.save(user.get());
		 }
		 return "Updated";
	 }
}

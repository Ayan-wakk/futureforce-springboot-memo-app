package com.lesson.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;    
	
	@GetMapping("/signup")
	public String signup(){
        return "admin/signup";		
	}
	
	@PostMapping("/signup")
	public String signup(Admin admin){
	
		//パスワードをハッシュ化してDBへ保存
		admin.setPassword(
			passwordEncoder.encode(admin.getPassword())
		);
		
		adminRepository.save(admin);
		
        return "redirect:/admin/signin";		
	}

	@GetMapping("/signin")
	public String signin(){
        return "admin/signin";		
	}
	
}
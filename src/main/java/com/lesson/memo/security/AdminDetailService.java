package com.lesson.memo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

//「ログインするときにユーザーを探す専門クラスです」とSpring Securityに伝える(ログイン認証担当)
@Service
public class AdminDetailService implements UserDetailsService{
	//Adminテーブルを検索できるようにするため
	@Autowired
	private AdminRepository adminRepository;

	//入力されたメールアドレスでAdminを探して、見つかったらSpring Security用のユーザー情報に変換して返す
	@Override
	public UserDetails loadUserByUsername(String email)
	    throws UsernameNotFoundException{
	    
		Admin admin = adminRepository.findByEmail(email)
	        .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));

	    return User.builder()
	               .username(admin.getEmail())
	               .password(admin.getPassword())
	               .roles("ADMIN")
	               .build();
	    
	}
}
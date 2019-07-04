package com.example.spring.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring.repository.RadnikRepository;

import model.Kategorija;
import model.Radnik;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private RadnikRepository radnikRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Radnik r = radnikRep.findByRadnikUsername(username);
		UserDetailsImp userDetails = new UserDetailsImp();
		userDetails.setPassword(r.getRadnikPassword());
		userDetails.setUsername(r.getRadnikUsername());
		Set<Kategorija>  kat = new HashSet<>();
		kat.add(r.getKategorija());
		userDetails.setRoles(kat);
		return userDetails;
	}
	
	
}

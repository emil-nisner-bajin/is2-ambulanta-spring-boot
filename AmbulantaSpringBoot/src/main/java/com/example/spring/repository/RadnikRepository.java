package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Kategorija;
import model.Radnik;

public interface RadnikRepository extends JpaRepository<Radnik, Integer> {

	public Radnik findByRadnikUsername(String username);
	
	public List<Radnik> findByKategorija(Kategorija k);
	
	@Query(value="Select r from Radnik r where r.kategorija.id = :idKategorije and r.activity != 0")
	public List<Radnik>	getRadnikForCategory(Integer idKategorije);
}

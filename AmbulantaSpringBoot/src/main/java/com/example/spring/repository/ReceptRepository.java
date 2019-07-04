package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Pacijent;
import model.Radnik;
import model.Recept;

public interface ReceptRepository extends JpaRepository<Recept, Integer> {

	public List<Recept> findByPacijent(Pacijent p);
	
	public List<Recept> findByRadnik(Radnik r);
}

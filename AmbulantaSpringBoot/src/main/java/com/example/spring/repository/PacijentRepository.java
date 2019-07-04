package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Integer> {

	public Pacijent findByPacijentUsername(String username);
	
	public Pacijent findByJmbg(String jmbg);
	
	@Query(value="Select p from Pacijent p where p.radnik.idRadnik = :idRadnik")
	public List<Pacijent> pacijenti(Integer idRadnik);
}

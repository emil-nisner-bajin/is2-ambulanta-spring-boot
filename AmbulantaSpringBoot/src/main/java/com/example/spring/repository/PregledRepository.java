package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Pacijent;
import model.Pregled;

public interface PregledRepository extends JpaRepository<Pregled, Integer> {
	
	public List<Pregled> findByPacijent(Pacijent p);
	
	@Query(value = "Select p from Pregled p where p.datum like :date and p.radnik.idRadnik = :id")
	public List<Pregled> getPregledsForDateAndDoctor(Integer id, String date);
	
	@Query(value = "Select p from Pregled p where p.radnik.idRadnik =: id")
	public List<Pregled> getAllPregledsForDoctor(Integer id);
	
	@Query(value="Select p from Pregled p where p.datum like :datum")
	public List<Pregled> getPregledsForDate(String datum);
	
}

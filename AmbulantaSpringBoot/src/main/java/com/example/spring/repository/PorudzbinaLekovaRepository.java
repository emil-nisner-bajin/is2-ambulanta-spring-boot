package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.PorudzbinaLekova;

public interface PorudzbinaLekovaRepository extends JpaRepository<PorudzbinaLekova, Integer> {

}

package com.example.spring.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.repository.KategorijaRepository;
import com.example.spring.repository.PacijentRepository;
import com.example.spring.repository.RadnikRepository;

import model.Kategorija;
import model.Pacijent;
import model.Radnik;

@Controller
@RequestMapping(value="Admin")
public class AdminController {

	@Autowired
	RadnikRepository radnikRep;
	
	@Autowired
	PacijentRepository pacijentRep;
	
	@Autowired
	KategorijaRepository kategorijaRep;
	
	
	@RequestMapping(value="dodajRadnika" , method=RequestMethod.POST)
	public String addRadnik(HttpServletRequest req, Integer kategorijaId ,String imeRadnika, String prezime, String usernameRadnik, String passwordRadnik) {
		Kategorija kategorija = kategorijaRep.findById(kategorijaId).get();
		Radnik r = new Radnik();
		
		r.setKategorija(kategorija);
		r.setIme(imeRadnika);
		r.setPrezime(prezime);
		r.setRadnikUsername(usernameRadnik);
		r.setRadnikPassword(passwordRadnik);
		r.setActivity(true);
		
		radnikRep.save(r);
		
		return "admin/dodavanjeRadnika";
	}
	
	@RequestMapping(value="obrisiRadnika", method=RequestMethod.POST)
	public String removeRadnik(HttpServletRequest req, Integer idRadnik) {
		Radnik r = radnikRep.findById(idRadnik).get();
		List<Pacijent> pacijenti = pacijentRep.pacijenti(idRadnik);
		for(Pacijent p: pacijenti) {
			p.setRadnik(null);
			pacijentRep.save(p);
		}
		r.setActivity(false);
		radnikRep.save(r);
		return "admin/administracijaPacijenata";
	}
	
	@RequestMapping(value="uzmiRadnike", method=RequestMethod.GET)
	public String fillRadnikComboBox(HttpServletRequest req) {
		List<Radnik> sviRadnici = radnikRep.findAll();
		List<Radnik> zaposleni = new LinkedList<>();
		for(Radnik r: sviRadnici) {
			if(r.getActivity()) {
				zaposleni.add(r);
			}
		}
		req.getSession().setAttribute("radnici", zaposleni);
		return "admin/brisanjeRadnika";
	}
	
	@RequestMapping(value="uzmiKategorije", method=RequestMethod.GET)
	public String fillKategorijaComboBox(HttpServletRequest req) {
		List<Kategorija> kategorije = kategorijaRep.findAll();
		req.getSession().setAttribute("kategorije", kategorije);
		return "admin/dodavanjeRadnika";
	}
	
	
	@RequestMapping(value="obrisiPacijenta", method=RequestMethod.POST)
	public String removePacijent(HttpServletRequest req, Integer idPacijent) {
		Pacijent p = pacijentRep.findById(idPacijent).get();
		p.setRadnik(null);
		p.setActivity(false);
		return "";
	}
}

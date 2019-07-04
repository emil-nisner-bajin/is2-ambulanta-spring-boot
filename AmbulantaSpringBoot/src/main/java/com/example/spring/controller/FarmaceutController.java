package com.example.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.repository.LekRepository;
import com.example.spring.repository.PorudzbinaLekovaRepository;
import com.example.spring.repository.ProizvodjacLekaRepository;

import model.Lek;
import model.PorudzbinaLekova;
import model.ProizvodjacLeka;
import model.Recept;

@Controller
@RequestMapping(value="Farmaceut")
public class FarmaceutController {
	
	private String nedovoljnaKolicinaNaStanju = "Greska! Nedovoljna kolicina na stanju!";
	
	@Autowired
	LekRepository lekRep;
	
	@Autowired
	ProizvodjacLekaRepository proizvodjacRep;
	
	@Autowired
	PorudzbinaLekovaRepository porudzbinaRep;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value = "dodajLek", method=RequestMethod.POST)
	public String addLek(HttpServletRequest req, String naziv, Integer kolicina, Double cena, Integer idProizvodjac) {
		
		ProizvodjacLeka p = proizvodjacRep.findById(idProizvodjac).get();
		
		
		Lek noviLek = new Lek();
		noviLek.setCenaPoKomadu(cena);
		noviLek.setKolicina(kolicina);
		noviLek.setNaziv(naziv);
		noviLek.setProizvodjacLeka(p);
	
		Lek saved = lekRep.save(noviLek);
		return "farmaceut/unosLeka";
	}
	
	@RequestMapping(value="izlistajPorudzbine", method=RequestMethod.GET)
	public String fillPorudzbine(HttpServletRequest req) {
		List<PorudzbinaLekova> result = porudzbinaRep.findAll();
		req.setAttribute("result", result);
		return "farmaceut/prikazPorudzbina";
	}
	
	@RequestMapping(value="popuniProizvodjace", method=RequestMethod.GET)
	public String fillProizvodjaci(HttpServletRequest req) {
		List<ProizvodjacLeka> result = proizvodjacRep.findAll();
		req.setAttribute("result", result);
		return "farmaceut/unosLeka";
	}
	
	@RequestMapping(value="popuniLekove", method=RequestMethod.GET)
	public String popuniLekove(HttpServletRequest req) {
		List<Lek> result = lekRep.findAll();
		//req.setAttribute("result", result);
		req.getSession().setAttribute("result", result);
		return "farmaceut/novaPorudzbina";
	}
	
	@RequestMapping(value = "dodajProizvodjaca", method=RequestMethod.GET)
	public String addProizvodjac(HttpServletRequest req, String nazivProizvodjaca) {
		ProizvodjacLeka p = new ProizvodjacLeka();
		p.setNaziv(nazivProizvodjaca);
		proizvodjacRep.save(p);
		return "farmaceut/dodajProizvodjaca";
	}
	
	@RequestMapping(value="poruciLek", method=RequestMethod.POST)
	public String dodajLekZaPorudzbinu(HttpServletRequest req, Integer kolicina, Integer idLek) {
		List<Lek> lekovi = (List<Lek>) req.getSession().getAttribute("porudzbinaLekova");
		if(lekovi == null) {
			lekovi = new LinkedList<>();
		}
		Lek referenca = lekRep.findById(idLek).get();
		Lek novi = new Lek();
		novi.setCenaPoKomadu(referenca.getCenaPoKomadu());
		novi.setKolicina(kolicina);
		novi.setProizvodjacLeka(referenca.getProizvodjacLeka());
		novi.setNaziv(referenca.getNaziv());
		lekovi.add(novi);
		
		req.getSession().setAttribute("porudzbinaLekova",lekovi);
		return "farmaceut/novaPorudzbina";
	}
	
	@RequestMapping(value = "napraviPorudzbinu", method=RequestMethod.POST)
	public String createPorudzbina(HttpServletRequest req) {
		List<Lek> listaLekova = (List<Lek>) req.getAttribute("listaLekova");
		String datumNarucivanja = new Date().toString();
		PorudzbinaLekova pl = new PorudzbinaLekova();
		pl.setDatumSlanja(datumNarucivanja);
		pl.setLeks(listaLekova);
		porudzbinaRep.save(pl);
		return "";
	}
	
	@RequestMapping(value = "primiPorudzbinu", method=RequestMethod.POST)
	public String primiPorudzbinu(HttpServletRequest req) {
		PorudzbinaLekova pl = (PorudzbinaLekova) req.getAttribute("porudzbinaLekova");
		String danasnjiDatum = new Date().toString();
		pl.setDatumPristizanja(danasnjiDatum);
		List<Lek> pristigli = pl.getLeks();
		for(Lek l : pristigli) {
			Integer id = l.getIdLek();
			Lek stanje = lekRep.findById(id).get();
			stanje.setKolicina(stanje.getKolicina()+l.getKolicina());
		}
		return "";
	}
	
	@RequestMapping(value = "zakljuciRecept", method=RequestMethod.POST )
	public String zakljuciRecept(HttpServletRequest req) {
		Recept recept = (Recept) req.getAttribute("recept");
		List<String> msg = new LinkedList<>();
		Lek l = recept.getLek();
		Integer idlek = l.getIdLek();
		Lek naStanju = lekRep.findById(idlek).get();
		Integer kolicinaNaStanju = naStanju.getKolicina();
		if(kolicinaNaStanju < l.getKolicina()) {
			msg.add(nedovoljnaKolicinaNaStanju);
			req.setAttribute("messages", msg);
			return "";
		}else {
			naStanju.setKolicina(kolicinaNaStanju-l.getKolicina());
			return "";
		}
	}

}

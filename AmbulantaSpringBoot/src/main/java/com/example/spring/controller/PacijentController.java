package com.example.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.repository.PacijentRepository;
import com.example.spring.repository.PregledRepository;
import com.example.spring.repository.RadnikRepository;
import com.example.spring.repository.ReceptRepository;

import model.Pacijent;
import model.Pregled;
import model.Radnik;
import model.Recept;

@Controller
@RequestMapping(value="Pacijent")
public class PacijentController {
	
	@Autowired
	PacijentRepository pacijentRep;
	
	@Autowired
	PregledRepository pregledRep;
	
	@Autowired
	ReceptRepository receptRep;
	
	@Autowired
	RadnikRepository radnikRep;
	
	private boolean check(String username) {
		Pacijent p = pacijentRep.findByPacijentUsername(username);
		if(p==null) {
			return false;
		} else {
			return true;
		}
	}
	
	@RequestMapping(value="odaberiLekara", method = RequestMethod.POST)
	public String changeLekar(HttpServletRequest req, Integer idLekar) {
		String username = (String) req.getSession().getAttribute("username");
		if(!check(username)) {
			return "pristupOdbijen";
		}
		Radnik r = radnikRep.findById(idLekar).get();
		Pacijent p = pacijentRep.findByPacijentUsername(username);
		p.setRadnik(r);
		pacijentRep.save(p);
		return "pacijent/pacijentMenu";
	}
	
	@RequestMapping(value="infoZakazivanjaPregleda", method = RequestMethod.GET)
	public String spremiZaZakazivanje(HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("username");
		if(!check(username)) {
			return "pristupOdbijen";
		}
		Pacijent p = pacijentRep.findByPacijentUsername(username);
		Radnik r = p.getRadnik();
		req.setAttribute("d", r);
		return "pacijent/zakaziPregled";
	}
	
	@RequestMapping(value="popuniLekare", method = RequestMethod.GET)
	public String fillLekarComboBox(HttpServletRequest req) {
		if(!check((String)req.getSession().getAttribute("username"))) {
			return "pristupOdbijen";
		}
		Integer idKategorije = 1;
		List<Radnik> radnici = radnikRep.getRadnikForCategory(idKategorije);
		req.setAttribute("radnici", radnici);
		return "pacijent/odaberiLekara";
	}
	
	@RequestMapping(value="pacijentWelcomePage", method = RequestMethod.GET)
	public String checkLekar(HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("username");
		if(!check(username)) {
			return "pristupOdbijen";
		}
		Pacijent p = pacijentRep.findByPacijentUsername(username);
		if(p.getRadnik() == null) {
			String msg = "Nemate odabranog lekara, kako bi ste bili u mogucnosti da zakazete pregled potrebno je odabrati lekara";
			req.setAttribute("msg", msg);
		}
		req.setAttribute("pacijent", p);
		return "pacijent/pacijentMenu";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value="zakazi", method = RequestMethod.POST)
	public String zakaziPregled(HttpServletRequest req, Date datum, String time) {
		Pacijent p = pacijentRep.findByPacijentUsername((String)req.getSession().getAttribute("username"));
		if(!check((String)req.getSession().getAttribute("username"))) {
			return "pristupOdbijen";
		}
		Integer idRadnik = p.getRadnik().getIdRadnik();
		Radnik r = radnikRep.findById(idRadnik).get();
		
		Pregled pregled = new Pregled();
		pregled.setDatum(datum.toString());
		pregled.setPacijent(p);
		pregled.setRadnik(r);
		pregled.setVreme(time);
		
		Pregled saved = pregledRep.save(pregled);
		return "pacijent/pacijentMenu";
	}
	
	@RequestMapping(value="prikaziRecepte", method = RequestMethod.GET)
	public String prikaziRecepte(HttpServletRequest req) {
		Pacijent p = pacijentRep.findByPacijentUsername((String)req.getSession().getAttribute("username"));
		if(!check((String)req.getSession().getAttribute("username"))) {
			return "pristupOdbijen";
		}
		List<Recept> result = receptRep.findByPacijent(p);
		req.setAttribute("recepti", result);
		return "pacijent/prikazRecepta";
	}
}

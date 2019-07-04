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

import com.example.spring.repository.LekRepository;
import com.example.spring.repository.PacijentRepository;
import com.example.spring.repository.PregledRepository;
import com.example.spring.repository.RadnikRepository;
import com.example.spring.repository.ReceptRepository;

import model.Lek;
import model.Pacijent;
import model.Pregled;
import model.Radnik;
import model.Recept;

@Controller
@RequestMapping(value="Doktor")
public class DoktorController {

	@Autowired
	PregledRepository pregledRepository;
	
	@Autowired
	PacijentRepository pacijentRepository;
	
	@Autowired
	RadnikRepository radnikRepository;
	
	@Autowired
	LekRepository lekRepository;
	
	@Autowired
	ReceptRepository receptRepository;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value="danasnjiPregledi", method=RequestMethod.GET)
	public String dansnjiPregledi(HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("username");
		Integer id = radnikRepository.findByRadnikUsername(username).getIdRadnik();
		List<Pregled> result = pregledRepository.getPregledsForDateAndDoctor(id, sdf.format(new Date()));
		req.setAttribute("result", result);
		return "doktor/prikazDanasnjihPregleda";
	}
	
	
	@RequestMapping(value="preglediZaDatum", method=RequestMethod.GET)
	public String getPreglediForDate(HttpServletRequest req, Date datum) {
		String username = (String) req.getSession().getAttribute("username");
		Integer id = radnikRepository.findByRadnikUsername(username).getIdRadnik();
		List<Pregled> result = pregledRepository.getPregledsForDateAndDoctor(id, sdf.format(datum));
		req.setAttribute("result", result);
		return "doktor/pretragaDatuma";
	}
	
	@RequestMapping(value="napisiPregled", method=RequestMethod.POST)
	public String preglediPacijenta(HttpServletRequest req, Integer pacijentId, String opis) {
		Radnik r = radnikRepository.findByRadnikUsername((String) req.getSession().getAttribute("username"));
		Pacijent p = pacijentRepository.findById(pacijentId).get();
		Pregled pregled = new Pregled();
		pregled.setDatum(sdf.format(new Date()));
		pregled.setVreme(new Date().getTime()+"");
		pregled.setPacijent(p);
		pregled.setRadnik(r);
		
		pregledRepository.save(pregled);
		req.setAttribute("pacijent", p);
		
		return "doktor/prikazProfilaPacijenta";
	}
	
	@RequestMapping(value="zakaziPregled", method=RequestMethod.POST)
	public String zakaziPregled(HttpServletRequest req, Date datumPregleda, Integer pacijentId, String vreme) {
		String usernameDoktor = (String) req.getSession().getAttribute("username");
		Radnik d = radnikRepository.findByRadnikUsername(usernameDoktor);
		Pacijent pac = pacijentRepository.findById(pacijentId).get();
		Pregled p = new Pregled();
		p.setDatum(sdf.format(datumPregleda));
		p.setPacijent(pac);
		p.setRadnik(d);
		p.setVreme(vreme);
		pregledRepository.save(p);
		req.setAttribute("pacijent", pac);
		return "doktor/prikazProfilaPacijenta";
	}
	
	@RequestMapping(value="izdajRecept", method=RequestMethod.POST)
	public String createRecept(HttpServletRequest req,Integer lekId, Integer pacijentId ) {
		String usernameDoktor = (String) req.getSession().getAttribute("username");
		Radnik doktor = radnikRepository.findByRadnikUsername(usernameDoktor);
		Pacijent p = pacijentRepository.findById(pacijentId).get();
		Lek l = lekRepository.findById(lekId).get();
		Recept recept = new Recept();
		recept.setLek(l);
		recept.setPacijent(p);
		recept.setRadnik(doktor);
		Recept saved = receptRepository.save(recept);
		req.setAttribute("savedRecept", saved);
		req.setAttribute("pacijent", p);
		return "doktor/prikazProfilaPacijenta";
	}
	
	@RequestMapping(value="nadjiPacijenta", method=RequestMethod.GET)
	public String findPacijent(HttpServletRequest req, String jmbg) {
		Pacijent p = pacijentRepository.findByJmbg(jmbg);
		req.setAttribute("pacijent", p);
		return "doktor/prikazProfilaPacijenta";
	}
	
	@RequestMapping(value="napisiReceptAkcija", method=RequestMethod.GET)
	public String napisiReceptAction(HttpServletRequest req, Integer pacijentId) {
		req.setAttribute("napisiRecept", "a");
		req.setAttribute("lekovi", lekRepository.findAll());
		req.setAttribute("pacijent", pacijentRepository.findById(pacijentId).get());
		return "doktor/prikazProfilaPacijenta";
	}
	
	@RequestMapping(value="zakaziPregledAkcija", method=RequestMethod.GET)
	public String zakaziPregledAction(HttpServletRequest req, Integer pacijentId) {
		req.setAttribute("zakaziPregled", "a");
		req.setAttribute("pacijent", pacijentRepository.findById(pacijentId).get());
		return "doktor/prikazProfilaPacijenta";
	}
	
	@RequestMapping(value="popuniPregledAkcija", method=RequestMethod.GET)
	public String popuniReceptAction(HttpServletRequest req, Integer pacijentId) {
		req.setAttribute("unesiPregled", "a");
		req.setAttribute("pacijent", pacijentRepository.findById(pacijentId).get());
		return "doktor/prikazProfilaPacijenta";
	}
}

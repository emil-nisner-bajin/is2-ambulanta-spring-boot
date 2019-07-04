package com.example.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.repository.LekRepository;
import com.example.spring.repository.PacijentRepository;
import com.example.spring.repository.ProizvodjacLekaRepository;
import com.example.spring.repository.RadnikRepository;
import com.example.spring.repository.ReceptRepository;

import model.Lek;
import model.Pacijent;
import model.ProizvodjacLeka;
import model.Radnik;
import model.Recept;

@Controller
@RequestMapping(value="Beans")
public class BeanController {
	
	@Autowired
	RadnikRepository radnikRep;
	
	@Autowired
	PacijentRepository pacijentRep;
	
	@Autowired
	ReceptRepository receptRep;
	
	@Autowired
	LekRepository lekRep;
	
	@Autowired
	ProizvodjacLekaRepository proizvodjacRep;
	
	public List<Radnik> radnikBean() {
		List<Radnik> result = radnikRep.findAll();
		return result;
	}
	
	@RequestMapping(value="pacijentBean", method=RequestMethod.GET)
	public String getAllPacijents(HttpServletRequest req) {
		List<Pacijent> result = pacijentRep.findAll();
		req.setAttribute("pacijenti", result);
		return "";
	}
	
	@RequestMapping(value="lekBean", method=RequestMethod.GET)
	public String getAllLeks(HttpServletRequest req) {
		List<Lek> res = lekRep.findAll();
		req.setAttribute("lekovi", res);
		return "";
	}
	
	@RequestMapping(value="proizvodjacLekovaBean", method=RequestMethod.GET)
	public String getAllProizvodjacs(HttpServletRequest req) {
		List<ProizvodjacLeka> res = proizvodjacRep.findAll();
		req.setAttribute("prozvodjaci", res);
		return "";
	}
	
	@RequestMapping(value="receptPacijentaBean", method=RequestMethod.GET)
	public String getReceptsPacijent(HttpServletRequest req) {
		Integer idPacijent = (Integer) req.getAttribute("idPacijent");
		Pacijent p = pacijentRep.findById(idPacijent).get();
		List<Recept> result = receptRep.findByPacijent(p);
		req.setAttribute("recepti", result);
		return "";
	}
	

}

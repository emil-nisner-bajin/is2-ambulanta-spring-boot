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

import com.example.spring.repository.KategorijaRepository;
import com.example.spring.repository.PacijentRepository;
import com.example.spring.repository.RadnikRepository;

import model.Kategorija;
import model.Pacijent;
import model.Radnik;

@Controller
@RequestMapping(value="Autentifikacija")
public class UserController {

	@Autowired
	KategorijaRepository katRep;
	
	@Autowired
	RadnikRepository radRep;
	
	@Autowired
	PacijentRepository pacRep;
	
	String pogresnaKombinacija = 		"Pogresno korisnicko ime/lozinka";
	String korisnickoImeVecPostoji = 	"Uneto korisnicko ime vec postoji!";
	String jmbgVecPostoji = 			"Nalog sa unetim jmbg-om vec postoji!";
	String registracijaUspela = 		"Registracija uspesno izvrsena";
	String lozinkaNeKonzistentna= 		"Greska! Lozinke nisu iste";
	
	@RequestMapping(value="Login" , method=RequestMethod.POST)
	public String checkLogin(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password"); 	// Odraditi ga kao char[45] zbog bezbednosti
		List<String> messages = new LinkedList<>(); 		//Povratne poruke o uspesnosti radnje
		boolean everythingOk = true;
		
		Radnik r = 		radRep.findByRadnikUsername(username);
		Pacijent p = 	pacRep.findByPacijentUsername(username);
		
		// Ne postoji korisnik sa tim korisnickim imenom
		if(p==null && r==null) {
			messages.add(pogresnaKombinacija);
			everythingOk=false;
		}
		
		if(everythingOk) {
			// Ako program dodje do ovog dela znaci da postoji takvo korisnicko ime u bazi
			// Proveravam da li je korisnicko ime od pacijenta
			if(p!=null && p.getActivity()) {
				if(p.getPacijentPassword().equals(password)) {
					req.getSession().setAttribute("username", p.getPacijentUsername());
				}else {
					messages.add(pogresnaKombinacija);
					everythingOk=false;
				}	
			// Ako nije od Pacijenta onda mora biti od nekog radnika
			}else {
				if(r.getRadnikPassword().equals(password) && r.getActivity()) {
					req.getSession().setAttribute("username", r.getRadnikUsername());
				}else {
					messages.add(pogresnaKombinacija);
					everythingOk=false;
				}
			}
		}
		
		if(everythingOk) {
			return menu(req);
		}else {
			req.setAttribute("messages", messages);
			req.setAttribute("reloaded", "r");
			return "./index";
		}
	}
	
	@RequestMapping(value="Register" , method=RequestMethod.POST)
	public String register(HttpServletRequest req) {
		String ime = 				(String) req.getParameter("pacijentIme");
		String prezime = 			(String) req.getParameter("pacijentPrezime");
		String jmbg = 				(String) req.getParameter("pacijentJmbg");
		String datumRodjenja = 		(String) req.getParameter("pacijentDatumRodjenja");
		String username = 			(String) req.getParameter("pacijentUsername");
		String password = 			(String) req.getParameter("pacijentPassword");
		String passwordRepeated = 	(String) req.getParameter("pacijentPasswordRepeat");
		
		
		boolean errorOccured = false;
		List<String> messages = new LinkedList<>();
		
		Pacijent tester = pacRep.findByJmbg(jmbg);
		if(tester != null) {
			messages.add(jmbgVecPostoji);
			errorOccured = true;
		}
		
		tester = pacRep.findByPacijentUsername(username);
		if(tester != null) {
			messages.add(korisnickoImeVecPostoji);
			errorOccured = true;
		}
		
		if(!password.equals(passwordRepeated)) {
			messages.add(lozinkaNeKonzistentna);
			errorOccured = true;
		}
		
		if(!errorOccured){
			Pacijent p = new Pacijent();
			p.setIme(ime);
			p.setPrezime(prezime);
			p.setJmbg(jmbg);
			p.setPacijentUsername(username);
			p.setPacijentPassword(password);
			p.setDatumRodjenja(datumRodjenja);
			p.setActivity(true);
			pacRep.save(p);
			messages.add(registracijaUspela);
		}
		req.setAttribute("messages", messages);
		req.setAttribute("reloaded", "r");
		return "./index";
	}
	
	@RequestMapping(value="Prijava", method=RequestMethod.GET)
	public String openLoginForm(HttpServletRequest req) {
		req.setAttribute("prijava", "");
		req.setAttribute("reloaded", "r");
		return "./index";
	}
	
	@RequestMapping(value="Registracija", method=RequestMethod.GET)
	public String openRegisterForm(HttpServletRequest req) {
		req.setAttribute("registracija", "");
		req.setAttribute("reloaded", "r");
		return "./index";
	}
	
	@RequestMapping(value="pristupOdbijen")
	public String pristupOdbijenStranica() {
		return "pristupOdbijen";
	}
	
	@RequestMapping(value = "mainMenu", method = RequestMethod.GET)
    public String menu(HttpServletRequest req) {    
    	String username = (String) req.getSession().getAttribute("username");
    	Radnik r = radRep.findByRadnikUsername(username);
    	if(r != null) {
    		Kategorija k = r.getKategorija();
    		switch(k.getKategorijaNaziv()) {
    		case "doktor":
    			return "doktor/doktorMenu";
    		case "farmaceut":
    			return "doktor/farmaceutMenu";
    		case "admin":
    			return "admin/AdminMenu";
    		default: return "pristupOdbijen";
    		}
    	}else {
    		Pacijent p = pacRep.findByPacijentUsername(username);
    		if(p != null) {
    			return "pacijent/pacijentMenu";
    		}else {
    			return "pristupOdbijen";
    		}
    	}
     
    }
	
	@RequestMapping(value = "loginPage", method = RequestMethod.GET)
    public String loginPage() {    
    	return "index";
     
    }
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
}

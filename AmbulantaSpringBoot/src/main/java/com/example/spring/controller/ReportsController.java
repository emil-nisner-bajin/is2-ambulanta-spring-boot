package com.example.spring.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.repository.PacijentRepository;
import com.example.spring.repository.RadnikRepository;

import model.Lek;
import model.Pacijent;
import model.PorudzbinaLekova;
import model.Radnik;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Controller
@RequestMapping(value="Reports")
public class ReportsController {
	
	@Autowired
	RadnikRepository repos;
	
	@Autowired
	PacijentRepository pacRep;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value="izvestajPorudzbine", method=RequestMethod.GET)
	public String generatePorudzbinaReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Lek> lekovi = (List<Lek>)request.getSession().getAttribute("porudzbinaLekova");
		PorudzbinaLekova por = new PorudzbinaLekova();
		por.setLeks(lekovi);
		por.setDatumSlanja(sdf.format(new Date()));
		por.setDatumPristizanja("");
		Radnik farmaceut = repos.findByRadnikUsername((String)request.getSession().getAttribute("username"));
		
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lekovi);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/porudzbinaLekova.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datumSlanja", por.getDatumSlanja());
		params.put("farmaceutIme", farmaceut.getIme());
		params.put("farmaceutPrezime", farmaceut.getPrezime());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=porudzbinaLekova.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
		return "farmaceut/novaPorudzbina";
	}
	
	@RequestMapping(value="izvestajPacijenata", method=RequestMethod.GET)
	public String generatePacijentReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Pacijent> pacijenti = pacRep.findAll();
		for(Pacijent p: pacijenti) {
			if(p.getRadnik()==null) {
				p.setRadnik( new Radnik());
				p.getRadnik().setIme("");
				p.getRadnik().setPrezime("");
			}
		}
		
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pacijenti);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/pacijentiAmbulante.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=pacijentiAmbulante.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
		return "admin/AdminMenu";
	}
}

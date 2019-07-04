package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the radnik database table.
 * 
 */
@Entity
@NamedQuery(name="Radnik.findAll", query="SELECT r FROM Radnik r")
public class Radnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRadnik;

	private boolean activity;

	private String ime;

	private String prezime;

	private String radnikPassword;

	private String radnikUsername;

	//bi-directional many-to-one association to Pacijent
	@OneToMany(mappedBy="radnik")
	private List<Pacijent> pacijents;

	//bi-directional many-to-one association to PorudzbinaLekova
	@OneToMany(mappedBy="radnik")
	private List<PorudzbinaLekova> porudzbinaLekovas;

	//bi-directional many-to-one association to Pregled
	@OneToMany(mappedBy="radnik")
	private List<Pregled> pregleds;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	private Kategorija kategorija;

	//bi-directional many-to-one association to Recept
	@OneToMany(mappedBy="radnik")
	private List<Recept> recepts;

	public Radnik() {
	}

	public int getIdRadnik() {
		return this.idRadnik;
	}

	public void setIdRadnik(int idRadnik) {
		this.idRadnik = idRadnik;
	}

	public boolean getActivity() {
		return this.activity;
	}

	public void setActivity(boolean activity) {
		this.activity = activity;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getRadnikPassword() {
		return this.radnikPassword;
	}

	public void setRadnikPassword(String radnikPassword) {
		this.radnikPassword = radnikPassword;
	}

	public String getRadnikUsername() {
		return this.radnikUsername;
	}

	public void setRadnikUsername(String radnikUsername) {
		this.radnikUsername = radnikUsername;
	}

	public List<Pacijent> getPacijents() {
		return this.pacijents;
	}

	public void setPacijents(List<Pacijent> pacijents) {
		this.pacijents = pacijents;
	}

	public Pacijent addPacijent(Pacijent pacijent) {
		getPacijents().add(pacijent);
		pacijent.setRadnik(this);

		return pacijent;
	}

	public Pacijent removePacijent(Pacijent pacijent) {
		getPacijents().remove(pacijent);
		pacijent.setRadnik(null);

		return pacijent;
	}

	public List<PorudzbinaLekova> getPorudzbinaLekovas() {
		return this.porudzbinaLekovas;
	}

	public void setPorudzbinaLekovas(List<PorudzbinaLekova> porudzbinaLekovas) {
		this.porudzbinaLekovas = porudzbinaLekovas;
	}

	public PorudzbinaLekova addPorudzbinaLekova(PorudzbinaLekova porudzbinaLekova) {
		getPorudzbinaLekovas().add(porudzbinaLekova);
		porudzbinaLekova.setRadnik(this);

		return porudzbinaLekova;
	}

	public PorudzbinaLekova removePorudzbinaLekova(PorudzbinaLekova porudzbinaLekova) {
		getPorudzbinaLekovas().remove(porudzbinaLekova);
		porudzbinaLekova.setRadnik(null);

		return porudzbinaLekova;
	}

	public List<Pregled> getPregleds() {
		return this.pregleds;
	}

	public void setPregleds(List<Pregled> pregleds) {
		this.pregleds = pregleds;
	}

	public Pregled addPregled(Pregled pregled) {
		getPregleds().add(pregled);
		pregled.setRadnik(this);

		return pregled;
	}

	public Pregled removePregled(Pregled pregled) {
		getPregleds().remove(pregled);
		pregled.setRadnik(null);

		return pregled;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<Recept> getRecepts() {
		return this.recepts;
	}

	public void setRecepts(List<Recept> recepts) {
		this.recepts = recepts;
	}

	public Recept addRecept(Recept recept) {
		getRecepts().add(recept);
		recept.setRadnik(this);

		return recept;
	}

	public Recept removeRecept(Recept recept) {
		getRecepts().remove(recept);
		recept.setRadnik(null);

		return recept;
	}

}
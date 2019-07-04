package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lek database table.
 * 
 */
@Entity
@NamedQuery(name="Lek.findAll", query="SELECT l FROM Lek l")
public class Lek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLek;

	private double cenaPoKomadu;

	private int kolicina;

	private String naziv;

	//bi-directional many-to-one association to ProizvodjacLeka
	@ManyToOne
	private ProizvodjacLeka proizvodjacLeka;

	//bi-directional many-to-many association to PorudzbinaLekova
	@ManyToMany(mappedBy="leks")
	private List<PorudzbinaLekova> porudzbinaLekovas;

	//bi-directional many-to-one association to Recept
	@OneToMany(mappedBy="lek")
	private List<Recept> recepts;

	public Lek() {
	}

	public int getIdLek() {
		return this.idLek;
	}

	public void setIdLek(int idLek) {
		this.idLek = idLek;
	}

	public double getCenaPoKomadu() {
		return this.cenaPoKomadu;
	}

	public void setCenaPoKomadu(double cenaPoKomadu) {
		this.cenaPoKomadu = cenaPoKomadu;
	}

	public int getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public ProizvodjacLeka getProizvodjacLeka() {
		return this.proizvodjacLeka;
	}

	public void setProizvodjacLeka(ProizvodjacLeka proizvodjacLeka) {
		this.proizvodjacLeka = proizvodjacLeka;
	}

	public List<PorudzbinaLekova> getPorudzbinaLekovas() {
		return this.porudzbinaLekovas;
	}

	public void setPorudzbinaLekovas(List<PorudzbinaLekova> porudzbinaLekovas) {
		this.porudzbinaLekovas = porudzbinaLekovas;
	}

	public List<Recept> getRecepts() {
		return this.recepts;
	}

	public void setRecepts(List<Recept> recepts) {
		this.recepts = recepts;
	}

	public Recept addRecept(Recept recept) {
		getRecepts().add(recept);
		recept.setLek(this);

		return recept;
	}

	public Recept removeRecept(Recept recept) {
		getRecepts().remove(recept);
		recept.setLek(null);

		return recept;
	}

}
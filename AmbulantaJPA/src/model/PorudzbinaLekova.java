package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the porudzbina_lekova database table.
 * 
 */
@Entity
@Table(name="porudzbina_lekova")
@NamedQuery(name="PorudzbinaLekova.findAll", query="SELECT p FROM PorudzbinaLekova p")
public class PorudzbinaLekova implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String datumPristizanja;

	private String datumSlanja;

	//bi-directional many-to-many association to Lek
	@ManyToMany
	@JoinTable(
		name="lek_has_porudzbina_lekova"
		, joinColumns={
			@JoinColumn(name="Porudzbina_Lekova_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Lek_idLek")
			}
		)
	private List<Lek> leks;

	//bi-directional many-to-one association to Radnik
	@ManyToOne
	private Radnik radnik;

	public PorudzbinaLekova() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatumPristizanja() {
		return this.datumPristizanja;
	}

	public void setDatumPristizanja(String datumPristizanja) {
		this.datumPristizanja = datumPristizanja;
	}

	public String getDatumSlanja() {
		return this.datumSlanja;
	}

	public void setDatumSlanja(String datumSlanja) {
		this.datumSlanja = datumSlanja;
	}

	public List<Lek> getLeks() {
		return this.leks;
	}

	public void setLeks(List<Lek> leks) {
		this.leks = leks;
	}

	public Radnik getRadnik() {
		return this.radnik;
	}

	public void setRadnik(Radnik radnik) {
		this.radnik = radnik;
	}

}
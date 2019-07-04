package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pregled database table.
 * 
 */
@Entity
@NamedQuery(name="Pregled.findAll", query="SELECT p FROM Pregled p")
public class Pregled implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPregled;

	private String datum;

	private String vreme;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	private Pacijent pacijent;

	//bi-directional many-to-one association to Radnik
	@ManyToOne
	private Radnik radnik;

	public Pregled() {
	}

	public int getIdPregled() {
		return this.idPregled;
	}

	public void setIdPregled(int idPregled) {
		this.idPregled = idPregled;
	}

	public String getDatum() {
		return this.datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return this.vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public Pacijent getPacijent() {
		return this.pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Radnik getRadnik() {
		return this.radnik;
	}

	public void setRadnik(Radnik radnik) {
		this.radnik = radnik;
	}

}
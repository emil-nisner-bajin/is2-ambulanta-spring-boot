package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the recept database table.
 * 
 */
@Entity
@NamedQuery(name="Recept.findAll", query="SELECT r FROM Recept r")
public class Recept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRecept;

	//bi-directional many-to-one association to Lek
	@ManyToOne
	private Lek lek;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	private Pacijent pacijent;

	//bi-directional many-to-one association to Radnik
	@ManyToOne
	private Radnik radnik;

	public Recept() {
	}

	public int getIdRecept() {
		return this.idRecept;
	}

	public void setIdRecept(int idRecept) {
		this.idRecept = idRecept;
	}

	public Lek getLek() {
		return this.lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
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
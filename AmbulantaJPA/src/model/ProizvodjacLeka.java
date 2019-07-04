package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proizvodjac_leka database table.
 * 
 */
@Entity
@Table(name="proizvodjac_leka")
@NamedQuery(name="ProizvodjacLeka.findAll", query="SELECT p FROM ProizvodjacLeka p")
public class ProizvodjacLeka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProizvodjacLeka;

	private String naziv;

	//bi-directional many-to-one association to Lek
	@OneToMany(mappedBy="proizvodjacLeka")
	private List<Lek> leks;

	public ProizvodjacLeka() {
	}

	public int getIdProizvodjacLeka() {
		return this.idProizvodjacLeka;
	}

	public void setIdProizvodjacLeka(int idProizvodjacLeka) {
		this.idProizvodjacLeka = idProizvodjacLeka;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Lek> getLeks() {
		return this.leks;
	}

	public void setLeks(List<Lek> leks) {
		this.leks = leks;
	}

	public Lek addLek(Lek lek) {
		getLeks().add(lek);
		lek.setProizvodjacLeka(this);

		return lek;
	}

	public Lek removeLek(Lek lek) {
		getLeks().remove(lek);
		lek.setProizvodjacLeka(null);

		return lek;
	}

}
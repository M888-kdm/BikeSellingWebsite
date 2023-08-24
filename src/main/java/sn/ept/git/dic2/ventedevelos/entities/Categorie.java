package sn.ept.git.dic2.ventedevelos.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;

    @JsonbTransient
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, orphanRemoval = true, mappedBy = "categorie")
    private Collection<Produit> produitCollection;

    public Categorie(){
    }

    public Categorie(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

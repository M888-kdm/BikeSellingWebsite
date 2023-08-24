package sn.ept.git.dic2.ventedevelos.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "marque")
public class Marque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "marque", orphanRemoval = true)
    private Collection<Produit> produitCollection;

    public Marque() {
    }
    public Marque(Integer id, String nom) {
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

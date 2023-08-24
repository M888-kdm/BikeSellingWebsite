/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.ventedevelos.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.eclipse.persistence.annotations.Index;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author pamoussa
 */
@Entity
@Table(name = "produit")
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id"),
    @NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nom = :nom"),
    @NamedQuery(name = "Produit.findByAnneeModel", query = "SELECT p FROM Produit p WHERE p.anneeModel = :anneeModel"),
    @NamedQuery(name = "Produit.findByPrixDepart", query = "SELECT p FROM Produit p WHERE p.prixDepart = :prixDepart")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @Column(name = "ANNEE_MODEL")
    private short anneeModel;
    @Basic(optional = false)
    @Column(name = "PRIX_DEPART")
    private BigDecimal prixDepart;

    @JoinColumn(name = "CATEGORIE_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private Categorie categorie;

    @JoinColumn(name = "MARQUE_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private Marque marque;

    @JsonbTransient
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "produit", orphanRemoval = true)
    private Collection<Stock> stockCollection;

    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "produit", orphanRemoval = true)
    private Collection<ArticleCommande> articleCommandeCollection;

    public Produit() {
    }

    public Produit(Integer id) {
        this.id = id;
    }

    public Produit(Integer id, String nom, short anneeModel, BigDecimal prixDepart) {
        this.id = id;
        this.nom = nom;
        this.anneeModel = anneeModel;
        this.prixDepart = prixDepart;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public short getAnneeModel() {
        return anneeModel;
    }

    public void setAnneeModel(short anneeModel) {
        this.anneeModel = anneeModel;
    }

    public BigDecimal getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(BigDecimal prixDepart) {
        this.prixDepart = prixDepart;
    }

    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produit[ id=" + id + " ]";
    }
    
}

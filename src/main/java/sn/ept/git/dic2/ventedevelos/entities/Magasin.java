/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.ventedevelos.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author pamoussa
 */
@Entity
@Table(name = "magasin")
@NamedQueries({
    @NamedQuery(name = "Magasin.findAll", query = "SELECT m FROM Magasin m"),
    @NamedQuery(name = "Magasin.findById", query = "SELECT m FROM Magasin m WHERE m.id = :id"),
    @NamedQuery(name = "Magasin.findByNom", query = "SELECT m FROM Magasin m WHERE m.nom = :nom"),
    @NamedQuery(name = "Magasin.findByTelephone", query = "SELECT m FROM Magasin m WHERE m.telephone = :telephone"),
    @NamedQuery(name = "Magasin.findByEmail", query = "SELECT m FROM Magasin m WHERE m.email = :email"),
    @NamedQuery(name = "Magasin.findByAdresse", query = "SELECT m FROM Magasin m WHERE m.adresse = :adresse"),
    @NamedQuery(name = "Magasin.findByVille", query = "SELECT m FROM Magasin m WHERE m.ville = :ville"),
    @NamedQuery(name = "Magasin.findByEtat", query = "SELECT m FROM Magasin m WHERE m.etat = :etat"),
    @NamedQuery(name = "Magasin.findByCodeZip", query = "SELECT m FROM Magasin m WHERE m.codeZip = :codeZip")})
public class Magasin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADRESSE")
    private String adresse;
    @Column(name = "VILLE")
    private String ville;
    @Column(name = "ETAT")
    private String etat;
    @Column(name = "CODE_ZIP")
    private String codeZip;

    @JsonbTransient
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "magasin", orphanRemoval = true)
    private Collection<Employe> employeCollection;

    @JsonbTransient
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "magasin", orphanRemoval = true)
    private Collection<Stock> stockCollection;

    public Magasin() {
    }

    public Magasin(Integer id) {
        this.id = id;
    }

    public Magasin(Integer id, String nom) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCodeZip() {
        return codeZip;
    }

    public void setCodeZip(String codeZip) {
        this.codeZip = codeZip;
    }

    public Collection<Employe> getEmployeCollection() {
        return employeCollection;
    }

    public void setEmployeCollection(Collection<Employe> employeCollection) {
        this.employeCollection = employeCollection;
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
        if (!(object instanceof Magasin)) {
            return false;
        }
        Magasin other = (Magasin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Magasin[ id=" + id + " ]";
    }
    
}

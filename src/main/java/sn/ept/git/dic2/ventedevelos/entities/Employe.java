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
@Table(name = "employe")
public class Employe extends Personne implements Serializable {

    @Basic(optional = false)
    @Column(name = "ACTIF")
    private short actif;

    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Employe manager;

    @JsonbTransient
    @OneToMany(cascade = { CascadeType.MERGE }, mappedBy = "manager")
    private Collection<Employe> employeCollection;

    @JsonbTransient
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "vendeurId")
    private Collection<Commande> ventesCollection;

    @JoinColumn(name = "MAGASIN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Magasin magasin;

    public Employe() {
    }

    public Employe(Integer id) {
        super(id);
    }

    public Employe(Integer id, String prenom, String nom, String email) {
        super(id, prenom, nom, email);
    }

    public Employe getManager() {
        return manager;
    }

    public void setManager(Employe manager) {
        this.manager = manager;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
}

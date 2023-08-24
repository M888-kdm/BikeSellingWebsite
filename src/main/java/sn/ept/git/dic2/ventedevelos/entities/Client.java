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
@Table(name = "client")
public class Client extends Personne implements Serializable{

    public Client() {
    }

    public Client(Integer id) {
        super(id);
    }

    public Client(Integer id, String prenom, String nom, String email) {
        super(id, prenom, nom, email);
    }

    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    @JsonbTransient
    @OneToMany(mappedBy = "clientId", orphanRemoval = false, cascade = { CascadeType.REMOVE, CascadeType.MERGE })
    private Collection<Commande> commandeCollection;

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.ventedevelos.entities;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author pamoussa
 */
@Entity
@Table(name = "commande")
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c"),
    @NamedQuery(name = "Commande.findByNumero", query = "SELECT c FROM Commande c WHERE c.numero = :numero"),
    @NamedQuery(name = "Commande.findByStatut", query = "SELECT c FROM Commande c WHERE c.statut = :statut"),
    @NamedQuery(name = "Commande.findByDateCommande", query = "SELECT c FROM Commande c WHERE c.dateCommande = :dateCommande"),
    @NamedQuery(name = "Commande.findByDateLivraisonVoulue", query = "SELECT c FROM Commande c WHERE c.dateLivraisonVoulue = :dateLivraisonVoulue"),
    @NamedQuery(name = "Commande.findByDateLivraison", query = "SELECT c FROM Commande c WHERE c.dateLivraison = :dateLivraison")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic(optional = true)
    @Column(name = "STATUT")
    private short statut;
    @Basic(optional = false)
    @Column(name = "DATE_COMMANDE")
    @Temporal(TemporalType.DATE)
    private Date dateCommande;

    @Basic(optional = false)
    @Column(name = "DATE_LIVRAISON_VOULUE")
    @Temporal(TemporalType.DATE)
    private Date dateLivraisonVoulue;

    @Basic(optional = false)
    @Column(name = "DATE_LIVRAISON")
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;

    @JsonbTransient
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "commande", orphanRemoval = true)
    private Collection<ArticleCommande> articleCommandeCollection;

    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Client clientId;
    @JoinColumn(name = "VENDEUR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Employe vendeurId;
    @JoinColumn(name = "MAGASIN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Magasin magasinId;

    public Commande() {
    }

    public Commande(Integer numero) {
        this.numero = numero;
    }

    public Commande(Integer numero, short statut, Date dateCommande, Date dateLivraisonVoulue) {
        this.numero = numero;
        this.statut = statut;
        this.dateCommande = dateCommande;
        this.dateLivraisonVoulue = dateLivraisonVoulue;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public short getStatut() {
        return statut;
    }

    public void setStatut(short statut) {
        this.statut = statut;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraisonVoulue() {
        return dateLivraisonVoulue;
    }

    public void setDateLivraisonVoulue(Date dateLivraisonVoulue) {
        this.dateLivraisonVoulue = dateLivraisonVoulue;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Collection<ArticleCommande> getArticleCommandeCollection() {
        return articleCommandeCollection;
    }

    public void setArticleCommandeCollection(Collection<ArticleCommande> articleCommandeCollection) {
        this.articleCommandeCollection = articleCommandeCollection;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Employe getVendeurId() {
        return vendeurId;
    }

    public void setVendeurId(Employe vendeurId) {
        this.vendeurId = vendeurId;
    }

    public Magasin getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Magasin magasinId) {
        this.magasinId = magasinId;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Commande[ numero=" + numero + " ]";
    }
    
}

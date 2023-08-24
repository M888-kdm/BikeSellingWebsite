/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.ventedevelos.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

/**
 *
 * @author pamoussa
 */
@Entity
@Table(name = "article_commande")
@NamedQueries({
        @NamedQuery(name = "ArticleCommande.findAll", query = "SELECT a FROM ArticleCommande a"),
        @NamedQuery(name = "ArticleCommande.findByNumeroCommande", query = "SELECT a FROM ArticleCommande a WHERE a.articleCommandePK.numeroCommande = :numeroCommande"),
        @NamedQuery(name = "ArticleCommande.findByLigne", query = "SELECT a FROM ArticleCommande a WHERE a.articleCommandePK.ligne = :ligne"),
        @NamedQuery(name = "ArticleCommande.findByProduitId", query = "SELECT a FROM ArticleCommande a WHERE a.produit = :produit"),
        @NamedQuery(name = "ArticleCommande.findByQuantite", query = "SELECT a FROM ArticleCommande a WHERE a.quantite = :quantite"),
        @NamedQuery(name = "ArticleCommande.findByPrixDepart", query = "SELECT a FROM ArticleCommande a WHERE a.prixDepart = :prixDepart"),
        @NamedQuery(name = "ArticleCommande.findByRemise", query = "SELECT a FROM ArticleCommande a WHERE a.remise = :remise")})
public class ArticleCommande implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverride(name = "ligne", column = @Column(name = "LIGNE"))
    protected ArticleCommandePK articleCommandePK;

    @Basic(optional = false)
    @Column(name = "QUANTITE")
    private int quantite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PRIX_DEPART")
    private BigDecimal prixDepart;
    @Basic(optional = false)
    @Column(name = "REMISE")
    private BigDecimal remise;

//    @Basic(optional = false)
//    @Column(name = "LIGNE")
//    private int ligne;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NUMERO_COMMANDE", referencedColumnName = "NUMERO")
    @MapsId("numeroCommande")
    private Commande commande;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUIT_ID", referencedColumnName = "ID")
    private Produit produit;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public ArticleCommande() {
    }

    public ArticleCommande(ArticleCommandePK articleCommandePK) {
        this.articleCommandePK = articleCommandePK;
    }

    public ArticleCommande(ArticleCommandePK articleCommandePK, Produit produit, int quantite, BigDecimal prixDepart, BigDecimal remise) {
        this.articleCommandePK = articleCommandePK;
        this.produit = produit;
        this.quantite = quantite;
        this.prixDepart = prixDepart;
        this.remise = remise;
    }

//    public int getLigne() {
//        return ligne;
//    }
//
//    public void setLigne(int ligne) {
//        this.ligne = ligne;
//    }

    public ArticleCommande(int numeroCommande, int ligne) {
        this.articleCommandePK = new ArticleCommandePK(numeroCommande, ligne);
    }

    public ArticleCommandePK getArticleCommandePK() {
        return articleCommandePK;
    }

    public void setArticleCommandePK(ArticleCommandePK articleCommandePK) {
        this.articleCommandePK = articleCommandePK;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(BigDecimal prixDepart) {
        this.prixDepart = prixDepart;
    }

    public BigDecimal getRemise() {
        return remise;
    }

    public void setRemise(BigDecimal remise) {
        this.remise = remise;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleCommandePK != null ? articleCommandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticleCommande)) {
            return false;
        }
        ArticleCommande other = (ArticleCommande) object;
        if ((this.articleCommandePK == null && other.articleCommandePK != null) || (this.articleCommandePK != null && !this.articleCommandePK.equals(other.articleCommandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ept.git.part1.entities.ArticleCommande[ articleCommandePK=" + articleCommandePK + " ]";
    }

}
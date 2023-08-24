/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.ventedevelos.entities;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 *
 * @author pamoussa
 */
@Entity
@Table(name = "stock")
@NamedQueries({
        @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
        @NamedQuery(name = "Stock.findByMagasinId", query = "SELECT s FROM Stock s WHERE s.stockPK.magasinId = :magasinId"),
        @NamedQuery(name = "Stock.findByProduitId", query = "SELECT s FROM Stock s WHERE s.stockPK.produitId = :produitId"),
        @NamedQuery(name = "Stock.findByQuantite", query = "SELECT s FROM Stock s WHERE s.quantite = :quantite")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StockPK stockPK;
    @Basic(optional = false)
    @Column(name = "QUANTITE")
    private int quantite;
    @JoinColumn(referencedColumnName = "ID", name = "PRODUIT_ID")
    @MapsId("produitId")
    @ManyToOne(optional = false)
    private Produit produit;

    @JoinColumn(referencedColumnName = "ID", name = "MAGASIN_ID")
    @ManyToOne(optional = false)
    @MapsId("magasinId")
    private Magasin magasin;

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Stock() {
    }

    public Stock(StockPK stockPK) {
        this.stockPK = stockPK;
    }

    public Stock(StockPK stockPK, int quantite) {
        this.stockPK = stockPK;
        this.quantite = quantite;
    }

    public Stock(int magasinId, int produitId) {
        this.stockPK = new StockPK(magasinId, produitId);
    }

    public StockPK getStockPK() {
        return stockPK;
    }

    public void setStockPK(StockPK stockPK) {
        this.stockPK = stockPK;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stockPK != null ? stockPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.stockPK == null && other.stockPK != null) || (this.stockPK != null && !this.stockPK.equals(other.stockPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ept.git.part1.entities.Stock[ stockPK=" + stockPK + " ]";
    }

}
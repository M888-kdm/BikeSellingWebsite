package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.Magasin;
import sn.ept.git.dic2.ventedevelos.entities.Produit;
import sn.ept.git.dic2.ventedevelos.entities.Stock;
import sn.ept.git.dic2.ventedevelos.facades.MagasinFacade;
import sn.ept.git.dic2.ventedevelos.facades.ProduitFacade;
import sn.ept.git.dic2.ventedevelos.facades.StockFacade;

import java.io.Serializable;
import java.util.List;

@Named("stockMBean")
@ViewScoped
public class StockMBean implements Serializable {

    @EJB
    private StockFacade stockFacade;

    @EJB
    private ProduitFacade produitFacade;

    @EJB
    private MagasinFacade magasinFacade;

    @PostConstruct
    public void init(){
        this.stock = new Stock();
    }

    private List<Stock> stocks;

    private Stock stock;

    private Integer magasinId;

    private Integer produitId;

    public void openNew() {
        this.stock = new Stock();
    }

    public List<Stock> getStocks() {
        if(stocks == null)
            stocks = stockFacade.findAll();
        return stocks;
    }

    public void save(){
        Produit produit = null;
        Magasin magasin = null;

        produit = produitFacade.find(produitId);
        magasin = magasinFacade.find(magasinId);

        stock.setProduit(produit);
        stock.setMagasin(magasin);
        
        if(stock.getStockPK() == null){
            stockFacade.create(stock);
            stocks.add(stock);
        }
        else {
            stockFacade.edit(stock);
        }
        this.stock = new Stock();
        PrimeFaces.current().executeScript("PF('manageStockDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-stocks");
    }

    public void deleteStock(){
        stocks.remove(stock);
        stockFacade.remove(stock);
        this.stock = null;
        PrimeFaces.current().ajax().update("form:dt-stocks");
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Integer magasinId) {
        this.magasinId = magasinId;
    }

    public Integer getProduitId() {
        return produitId;
    }

    public void setProduitId(Integer produitId) {
        this.produitId = produitId;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}

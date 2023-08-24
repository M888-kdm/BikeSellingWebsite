package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import sn.ept.git.dic2.ventedevelos.entities.Categorie;
import sn.ept.git.dic2.ventedevelos.entities.Magasin;
import sn.ept.git.dic2.ventedevelos.entities.Marque;
import sn.ept.git.dic2.ventedevelos.entities.Produit;
import sn.ept.git.dic2.ventedevelos.facades.CategorieFacade;
import sn.ept.git.dic2.ventedevelos.facades.MarqueFacade;
import sn.ept.git.dic2.ventedevelos.facades.ProduitFacade;
import sn.ept.git.dic2.ventedevelos.facades.StockFacade;

import java.io.Serializable;
import java.util.List;

@Named("produitMBean")
@ViewScoped
public class ProduitsMBean implements Serializable {

    private LazyDataModel<Produit> lazyModel;

    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private MarqueFacade marqueFacade;
    @EJB
    private CategorieFacade categorieFacade;

    @PostConstruct
    public void init(){
        this.selectedProduct = new Produit();
        lazyModel = new LazyProduitDataModel(produitFacade.findAll());
    }

    private List<Produit> produits;
    private List<Produit> selectedProducts;
    private Produit selectedProduct;
    private Integer marqueId;
    private Integer categorieId;

    private Marque marque;

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public void openNew() {
        this.selectedProduct = new Produit();
    }

    public void deleteProduct(){
        this.produits.remove(this.selectedProduct);
        produitFacade.remove(this.selectedProduct);
        if(this.selectedProducts != null)
            this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        PrimeFaces.current().ajax().update("form:dt-products");
    }

    public List<Produit> getProduits() {
        if(produits == null)
            produits = produitFacade.findAll();
        return produits;
    }

    public LazyDataModel<Produit> getLazyModel() {
        return lazyModel;
    }

    public void save(){
        Marque marque = null;
        Categorie categorie = null;

        marque = marqueFacade.find(marqueId);
        categorie = categorieFacade.find(categorieId);

        selectedProduct.setCategorie(categorie);
        selectedProduct.setMarque(marque);

        if(selectedProduct.getId() == null){
            produitFacade.create(selectedProduct);
            produits.add(selectedProduct);
        }
        else{
            produits.remove(selectedProduct);
            produitFacade.edit(selectedProduct);
            produits.add(selectedProduct);
        }

        this.selectedProduct = null;
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-products");
    }

    public List<Produit> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Produit> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public Produit getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Produit selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Integer getMarqueId() {
        return marqueId;
    }

    public void setMarqueId(Integer marqueId) {
        this.marqueId = marqueId;
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
    }
}

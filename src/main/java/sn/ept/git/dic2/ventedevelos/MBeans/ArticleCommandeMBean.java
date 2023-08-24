package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventedevelos.entities.Commande;
import sn.ept.git.dic2.ventedevelos.entities.Produit;
import sn.ept.git.dic2.ventedevelos.facades.ArticleCommandeFacade;
import sn.ept.git.dic2.ventedevelos.facades.CommandeFacade;
import sn.ept.git.dic2.ventedevelos.facades.ProduitFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named("articleCommandeMBean")
@ViewScoped
public class ArticleCommandeMBean implements Serializable {

    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    @EJB
    private ProduitFacade produitFacade;

    @EJB
    private CommandeFacade commandeFacade;

    @PostConstruct
    public void init(){
        this.articleCommande = new ArticleCommande();
    }
    private List<ArticleCommande> articleCommandes;
    private ArticleCommande articleCommande;
    private Integer produitId;
    private Integer commandeId;

    public void openNew() {
        this.articleCommande = new ArticleCommande();
    }

    public List<ArticleCommande> getArticleCommandes() {
        if(articleCommandes == null)
            articleCommandes = articleCommandeFacade.findAll();
        return articleCommandes;
    }

    public void save(){
        Produit produit = produitFacade.find(produitId);
        Commande commande = commandeFacade.find(commandeId);
        BigDecimal prixDepart = articleCommande.getProduit().getPrixDepart().multiply(BigDecimal.valueOf(articleCommande.getQuantite()));

        articleCommande.setProduit(produit);
        articleCommande.setPrixDepart(prixDepart);
        articleCommande.setCommande(commande);

        if(articleCommande.getArticleCommandePK() == null){
            articleCommandeFacade.create(articleCommande);
            articleCommandes.add(articleCommande);
        }
        else {
            articleCommandeFacade.edit(articleCommande);
        }
        this.articleCommande = new ArticleCommande();
        PrimeFaces.current().executeScript("PF('manageArticleCommandeDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-articleCommandes");
    }

    public void deleteArticleCommande(){
        articleCommandes.remove(articleCommande);
        articleCommandeFacade.remove(articleCommande);
        this.articleCommande = null;
        PrimeFaces.current().ajax().update("form:dt-articleCommandes");
    }

    public ArticleCommande getArticleCommande() {
        return articleCommande;
    }

    public void setArticleCommande(ArticleCommande articleCommande) {
        this.articleCommande = articleCommande;
    }

    public Integer getProduitId() {
        return produitId;
    }

    public void setProduitId(Integer produitId) {
        this.produitId = produitId;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }
}

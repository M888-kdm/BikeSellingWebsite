package sn.ept.git.dic2.ventedevelos.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
public class ProduitDTO implements Serializable {

    public Integer id;
    public String nom;
    public short anneeModel;
    public BigDecimal prixDepart;
    public Integer categorieId;
    public Integer marqueId;


}

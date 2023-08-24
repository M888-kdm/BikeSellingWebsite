package sn.ept.git.dic2.ventedevelos.MBeans;

import org.primefaces.model.SortOrder;
import sn.ept.git.dic2.ventedevelos.entities.Produit;

import java.util.Comparator;

import static sn.ept.git.dic2.ventedevelos.MBeans.LazyProduitDataModel.getPropertyValueViaReflection;

public class LazySorter implements Comparator<Produit> {

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Produit produit1, Produit produit2) {
        try {
            Object value1 = getPropertyValueViaReflection(produit1, sortField);
            Object value2 = getPropertyValueViaReflection(produit2, sortField);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

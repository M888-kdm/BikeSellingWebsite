package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.faces.context.FacesContext;
import org.apache.commons.collections4.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;
import sn.ept.git.dic2.ventedevelos.entities.Produit;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LazyProduitDataModel extends LazyDataModel<Produit> {

    private static final long serialVersionUID = 1L;

    private List<Produit> datasource;

    public LazyProduitDataModel(List<Produit> datasource) {
        this.datasource = datasource;
    }

    public static final Object getPropertyValueViaReflection(Object o, String field)
            throws ReflectiveOperationException, IllegalArgumentException, IntrospectionException {
        return new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o);
    }

    @Override
    public Produit getRowData(String rowKey) {
        for (Produit product : datasource) {
            if (product.getId() == Integer.parseInt(rowKey)) {
                return product;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Produit product) {
        return String.valueOf(product.getId());
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return (int) datasource.stream()
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .count();
    }

    @Override
    public List<Produit> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        // apply offset & filters
        List<Produit> products = datasource.stream()
                .skip(offset)
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .limit(pageSize)
                .collect(Collectors.toList());

        // sort
        if (!sortBy.isEmpty()) {
            List<Comparator<Produit>> comparators = sortBy.values().stream()
                    .map(o -> new LazySorter(o.getField(), o.getOrder()))
                    .collect(Collectors.toList());
            Comparator<Produit> cp = ComparatorUtils.chainedComparator(comparators); // from apache
            products.sort(cp);
        }

        return products;
    }

    private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for (FilterMeta filter : filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                Object columnValue = String.valueOf(getPropertyValueViaReflection(o, filter.getField()));
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            }
            catch (ReflectiveOperationException | IntrospectionException e) {
                matching = false;
            }

            if (!matching) {
                break;
            }
        }

        return matching;
    }

}

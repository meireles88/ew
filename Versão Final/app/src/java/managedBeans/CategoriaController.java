package managedBeans;

import entityClasses.Categoria;
import managedBeans.util.JsfUtil;
import facade.CategoriaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("categoriaController")
@SessionScoped
public class CategoriaController implements Serializable {

    private Categoria selected;
    private DataModel items = null;
    private List<Categoria> itemsToConvert;
    private List<Categoria> filtered;
    @EJB
    private facade.CategoriaFacade ejbFacade;
    private SelectItem[] categoriaOptions;

    public SelectItem[] getCategoriaOptions() {

        items = this.getItems();

        Iterator<Categoria> it = items.iterator();
        ArrayList<Categoria> aux = new ArrayList<Categoria>();
        while (it.hasNext()) {
            Categoria c = (Categoria) it.next();
            aux.add(c);
        }

        SelectItem[] options = new SelectItem[aux.size() + 1];

        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < aux.size(); i++) {
            options[i + 1] = new SelectItem(aux.get(i), aux.get(i).getTitulo());
        }

        this.categoriaOptions = options;
        return this.categoriaOptions;
    }

    public void setCategoriaOptions(SelectItem[] categoriaOptions) {
        this.categoriaOptions = categoriaOptions;
    }

    public CategoriaController() {
    }

    public List<Categoria> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<Categoria> items) {
        this.itemsToConvert = items;
    }

    public Categoria getSelected() {
        return selected;
    }

    public void setSelected(Categoria c) {
        this.selected = c;
    }

    private CategoriaFacade getFacade() {
        return ejbFacade;
    }

    public List<Categoria> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Categoria> filtered) {
        this.filtered = filtered;
    }

    public String prepareList() {
        this.selected = null;
        recreateModel();
        return "List";
    }

    public String prepareView() {

        if (this.selected != null) {
            return "View";
        }
        JsfUtil.addErrorMessage(new Exception(), ResourceBundle.getBundle("/Bundle").getString("NoSelectedRow"));
        return null;
    }

    public String prepareCreateFromMenu() {
        selected = new Categoria();
        return "/admin/categoria/Create?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = new Categoria();
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {

            this.selected.setDatainsercao(new Date());

            getFacade().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoriaCreated"));
            recreateModel();
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        return "List";
    }

    public String prepareEdit() {

        if (this.selected != null) {
            return "Edit";
        }
        JsfUtil.addErrorMessage(new Exception(), ResourceBundle.getBundle("/Bundle").getString("NoSelectedRow"));
        return null;
    }

    public String update() {

        try {

            this.selected.setDataedicao(new Date());

            getFacade().edit(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoriaUpdated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {

        performDestroy();
        recreateModel();
        return "List";
    }

    private void performDestroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoriaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public DataModel getItems() {

        return new ListDataModel(getFacade().findAll());
    }

    private void recreateModel() {
        items = null;
    }

    public Categoria getCategoria(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("CategoriaConverter")
    public static class CategoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriaController controller = (CategoriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriaController");
            return controller.getCategoria(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Categoria) {
                Categoria o = (Categoria) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Categoria.class.getName());
            }
        }

    }

}

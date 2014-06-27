package managedBeans;

import entityClasses.Voto;
import managedBeans.util.JsfUtil;
import facade.VotoFacade;
import java.io.Serializable;
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

@Named("votoController")
@SessionScoped
public class VotoController implements Serializable {

    private Voto selected;
    private DataModel items = null;
    private List<Voto> itemsToConvert;
    private List<Voto> filtered;
    @EJB
    private facade.VotoFacade ejbFacade;

    public VotoController() {
    }

    public List<Voto> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<Voto> items) {
        this.itemsToConvert = items;
    }

    public Voto getSelected() {
        return selected;
    }

    public void setSelected(Voto c) {
        this.selected = c;
    }

    private VotoFacade getFacade() {
        return ejbFacade;
    }

    public List<Voto> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Voto> filtered) {
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
        selected = new Voto();
        return "voto/Create?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = new Voto();
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VotoCreated"));
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
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VotoUpdated"));
            return "View";
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VotoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(getFacade().findAll());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public Voto getVoto(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("VotoConverter")
    public static class VotoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VotoController controller = (VotoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "votoController");
            return controller.getVoto(getKey(value));
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
            if (object instanceof Voto) {
                Voto o = (Voto) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Voto.class.getName());
            }
        }

    }

}

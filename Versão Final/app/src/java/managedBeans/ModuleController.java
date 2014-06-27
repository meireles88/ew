package managedBeans;

import entityClasses.Module;
import managedBeans.util.JsfUtil;
import facade.ModuleFacade;
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

@Named("moduleController")
@SessionScoped
public class ModuleController implements Serializable {

    private Module selected;
    private DataModel items = null;
    private List<Module> itemsToConvert;
    private List<Module> filtered;
    @EJB
    private facade.ModuleFacade ejbFacade;

    public ModuleController() {
    }

    public List<Module> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<Module> items) {
        this.itemsToConvert = items;
    }

    public Module getSelected() {
        return selected;
    }

    public void setSelected(Module c) {
        this.selected = c;
    }

    private ModuleFacade getFacade() {
        return ejbFacade;
    }

    public List<Module> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Module> filtered) {
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
        selected = new Module();
        return "module/Create?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = new Module();
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ModuleCreated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ModuleUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ModuleDeleted"));
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

    public Module getModule(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("ModuleConverter")
    public static class ModuleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModuleController controller = (ModuleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "moduleController");
            return controller.getModule(getKey(value));
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
            if (object instanceof Module) {
                Module o = (Module) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Module.class.getName());
            }
        }

    }

}

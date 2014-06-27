package managedBeans;

import entityClasses.Tag;
import managedBeans.util.JsfUtil;
import facade.TagFacade;
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

@Named("tagController")
@SessionScoped
public class TagController implements Serializable {

    private Tag selected;
    private DataModel items = null;
    private List<Tag> itemsToConvert;
    private List<Tag> filtered;
    @EJB
    private facade.TagFacade ejbFacade;

    public TagController() {
    }

    public List<Tag> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<Tag> items) {
        this.itemsToConvert = items;
    }

    public Tag getSelected() {
        return selected;
    }

    public void setSelected(Tag c) {
        this.selected = c;
    }

    private TagFacade getFacade() {
        return ejbFacade;
    }

    public List<Tag> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Tag> filtered) {
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
        selected = new Tag();
        return "tag/Create?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = new Tag();
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TagCreated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TagUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TagDeleted"));
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

    public Tag getTag(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("TagConverter")
    public static class TagControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TagController controller = (TagController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tagController");
            return controller.getTag(getKey(value));
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
            if (object instanceof Tag) {
                Tag o = (Tag) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Tag.class.getName());
            }
        }

    }

}

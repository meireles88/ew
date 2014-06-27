package managedBeans;

import entityClasses.Group1;
import entityClasses.User;
import managedBeans.util.JsfUtil;
import facade.Group1Facade;
import java.io.Serializable;
import java.util.ArrayList;
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

@Named("group1Controller")
@SessionScoped
public class Group1Controller implements Serializable {

    private Group1 selected;
    private DataModel items = null;
    private List<Group1> itemsToConvert;
    private List<Group1> filtered;
    @EJB
    private facade.Group1Facade ejbFacade;
            private SelectItem[] userOptions;

    public SelectItem[] getUserOptions() {

        items = this.getItems();

        Iterator<Group1> it = items.iterator();
        ArrayList<Group1> aux = new ArrayList<Group1>();
        while (it.hasNext()) {
            Group1 c = (Group1) it.next();
            aux.add(c);
        }

        SelectItem[] options = new SelectItem[aux.size() + 1];

        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < aux.size(); i++) {
            options[i + 1] = new SelectItem(aux.get(i), aux.get(i).getGroupname());
        }

        this.userOptions = options;
        return this.userOptions;
    }

    public void setUserOptions(SelectItem[] userOptions) {
        this.userOptions = userOptions;
    }

    public Group1Controller() {
    }

    public List<Group1> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<Group1> items) {
        this.itemsToConvert = items;
    }

    public Group1 getSelected() {
        return selected;
    }

    public void setSelected(Group1 c) {
        this.selected = c;
    }

    private Group1Facade getFacade() {
        return ejbFacade;
    }

    public List<Group1> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Group1> filtered) {
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
        selected = new Group1();
        return "group1/Create?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = new Group1();
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Group1Created"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Group1Updated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Group1Deleted"));
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

    public Group1 getGroup1(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("Group1Converter")
    public static class Group1ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Group1Controller controller = (Group1Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "group1Controller");
            return controller.getGroup1(getKey(value));
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
            if (object instanceof Group1) {
                Group1 o = (Group1) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Group1.class.getName());
            }
        }

    }

}

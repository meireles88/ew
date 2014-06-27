package managedBeans;

import entityClasses.Grupoorcamental;
import managedBeans.util.JsfUtil;
import facade.GrupoorcamentalFacade;
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

@Named("grupoorcamentalController")
@SessionScoped
public class GrupoorcamentalController implements Serializable {

    private Grupoorcamental selected;
    private DataModel items = null;
    private List<Grupoorcamental> itemsToConvert;
    private List<Grupoorcamental> filtered;
    @EJB
    private facade.GrupoorcamentalFacade ejbFacade;
    private SelectItem[] grupoOrcamentalOptions;

    public GrupoorcamentalController() {
    }
    
        public SelectItem[] getGrupoOrcamentalOptions() {

        items = this.getItems();

        Iterator<Grupoorcamental> it = items.iterator();
        ArrayList<Grupoorcamental> aux = new ArrayList<Grupoorcamental>();
        while (it.hasNext()) {
            Grupoorcamental g = (Grupoorcamental) it.next();
            aux.add(g);
        }

        SelectItem[] options = new SelectItem[aux.size() + 1];

        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < aux.size(); i++) {
            options[i + 1] = new SelectItem(aux.get(i), aux.get(i).getTitulo());
        }

        this.grupoOrcamentalOptions = options;
        return this.grupoOrcamentalOptions;
    }

    public List<Grupoorcamental> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<Grupoorcamental> items) {
        this.itemsToConvert = items;
    }

    public Grupoorcamental getSelected() {
        return selected;
    }

    public void setSelected(Grupoorcamental c) {
        this.selected = c;
    }

    private GrupoorcamentalFacade getFacade() {
        return ejbFacade;
    }

    public List<Grupoorcamental> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Grupoorcamental> filtered) {
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

    public String prepareCreate() {
        selected = new Grupoorcamental();
        return "Create?faces-redirect=true";
    }
    
            public String prepareCreateFromMenu() {
        selected = new Grupoorcamental();
        return "/admin/grupoorcamental/Create?faces-redirect=true";
    }

    public String create() {
        try {
            
            this.selected.setDatainsercao(new Date());
            
            getFacade().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GrupoorcamentalCreated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GrupoorcamentalUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GrupoorcamentalDeleted"));
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

    public Grupoorcamental getGrupoorcamental(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("GrupoorcamentalConverter")
    public static class GrupoorcamentalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GrupoorcamentalController controller = (GrupoorcamentalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "grupoorcamentalController");
            return controller.getGrupoorcamental(getKey(value));
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
            if (object instanceof Grupoorcamental) {
                Grupoorcamental o = (Grupoorcamental) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Grupoorcamental.class.getName());
            }
        }

    }

}

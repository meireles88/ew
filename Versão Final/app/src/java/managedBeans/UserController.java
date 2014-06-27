package managedBeans;

import entityClasses.Group1;
import entityClasses.User;
import managedBeans.util.JsfUtil;
import facade.UserFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    private User selected;
    private DataModel items = null;
    private List<User> itemsToConvert;
    private List<User> filtered;
    @EJB
    private facade.UserFacade ejbFacade;
    @EJB
    private facade.Group1Facade groupEjbFacade;
    private UploadedFile uploadedFile;
    private int nrDiasBan;



    public UserController() {
    }

    public int getNrDiasBan() {
        return nrDiasBan;
    }

    public void setNrDiasBan(int nrDiasBan) {
        this.nrDiasBan = nrDiasBan;
    }
    
    
    
    public void handleFileUpload(FileUploadEvent event) {
    FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
    FacesContext.getCurrentInstance().addMessage(null, msg);

    InputStream is;
    try {
        File file = new File("/Users/wolvz/Desktop/uploads/"+event.getFile().getFileName());
        this.selected.setFoto(event.getFile().getFileName());
        is = event.getFile().getInputstream();
        OutputStream os = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0) {
            os.write(buf, 0, len);
        }
        os.close();
        is.close();

    } catch (IOException ex) {
        System.out.println(ex.getStackTrace());
    }
}
   


    public List<User> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<User> items) {
        this.itemsToConvert = items;
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User c) {
        this.selected = c;
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public List<User> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<User> filtered) {
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

    public String view2(User u) {

        this.selected = u;
        String s = null;
        
        FacesContext context = FacesContext.getCurrentInstance();
            UserSession us = (UserSession)context.getExternalContext().getSessionMap().get("userSession");
        
        if(us.getUser().getGroupOid().getGroupname().equals("URegistado")) return "/faces/uregistado/user/View.xhtml?faces-redirect=true";
        if(us.getUser().getGroupOid().getGroupname().equals("Moderador")) return "/faces/mod/user/View.xhtml?faces-redirect=true";
        if(us.getUser().getGroupOid().getGroupname().equals("Administrador")) return "/faces/admin/user/View.xhtml?faces-redirect=true";
        return "";

    }
   

    public String edit2(User u) {
        this.selected = u;
        String s = null;
        System.out.println(u.getGroupOid().getGroupname());
        
        if(u.getGroupOid().getGroupname().equals("URegistado")) return "/faces/uregistado/user/Edit.xhtml?faces-redirect=true";
        if(u.getGroupOid().getGroupname().equals("Moderador")) return "/faces/mod/user/Edit.xhtml?faces-redirect=true";
        if(u.getGroupOid().getGroupname().equals("Administrador")) return "/faces/admin/user/Edit.xhtml?faces-redirect=true";
        return "";

    }

    public String prepareCreateFromMenu() {
        selected = new User();
        return "user/Create?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = new User();
        return "Create?faces-redirect=true";
    }

    public String prepareCreate2() {
        selected = new User();
        return "registar";
    }

    public String create() {
        try {

            this.selected.setDatacriacao(new Date());
            Group1 g = this.groupEjbFacade.findByGroupname("URegistado");
            this.selected.setGroupOid(g);

            getFacade().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
            recreateModel();
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String create2() {
        try {

            this.selected.setDatacriacao(new Date());
            Group1 g = this.groupEjbFacade.findByGroupname("URegistado");
            this.selected.setGroupOid(g);

            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registado com sucesso! Faça login para aceder à plataforma.");
            recreateModel();
            return "login";
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

            this.selected.setDataultimaedicao(new Date());

            getFacade().edit(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
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

    public User getUser(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("UserConverter")
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
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
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }
    
    public void setBanDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, this.nrDiasBan);
        this.selected.setDataban(cal.getTime());
        getFacade().edit(selected);
        JsfUtil.addSuccessMessage("Utilizador banido por "+this.nrDiasBan+" dias.");
    }

}

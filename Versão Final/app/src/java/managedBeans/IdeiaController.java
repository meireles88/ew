package managedBeans;

import entityClasses.Ideia;
import entityClasses.Tag;
import entityClasses.User;
import entityClasses.Voto;
import managedBeans.util.JsfUtil;
import facade.IdeiaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


@Named("ideiaController")
@SessionScoped
public class IdeiaController implements Serializable {

    private Ideia selected;
    private DataModel items = null;
    private DataModel itemsApproved = null;
    private DataModel itemsNotApproved = null;

    private List<Ideia> itemsToConvert;
    private List<Ideia> filtered;
    private List<Ideia> filteredSugeridas;
    private List<Ideia> filteredInseridas;
    private List<Ideia> filteredVotos;
    private List<Ideia> filteredAprovar;
    private DataModel userIdeias;
    private DataModel userIdeiasVotou;
    @EJB
    private facade.IdeiaFacade ejbFacade;
    @EJB
    private facade.VotoFacade ejbVotoFacade;
    private boolean flag = false;
    private List<String> selectedThemes;
    private List<String> selectedThemes2;
    @EJB
    private facade.TagFacade ejbFacadet;
    private User uprofile;

    public IdeiaController() {
    }

    public User getUprofile() {
        return uprofile;
    }

    public void setUprofile(User uprofile) {
        this.uprofile = uprofile;
    }

    public List<String> completeTheme(String query) {

        ArrayList<String> themes = new ArrayList<String>();
        List<Tag> aux = ejbFacadet.findAll();
        int i = 0;
        while (i < aux.size()) {
            themes.add(aux.get(i).getNome());
            i++;
        }

        List<String> filteredThemes = new ArrayList<String>();

        boolean encontrado = false;

        for (i = 0; i < themes.size(); i++) {
            String s = themes.get(i);
            if (s.toLowerCase().startsWith(query)) {
                filteredThemes.add(s);
                encontrado = true;
            }
        }
        if (!encontrado) {
            filteredThemes.add(query);
        }

        return filteredThemes;
    }

    public List<String> getSelectedThemes() {
        return selectedThemes;
    }

    public List<String> getSelectedThemes2() {

        ArrayList<String> aux = new ArrayList<String>();
        for (Tag t : this.selected.getTagCollection()) {
            aux.add(t.getNome());
        }
        this.selectedThemes2 = aux;
        return aux;
        //return selectedThemes2;
    }

    public void setSelectedThemes2(List<String> selectedThemes) {
        this.selectedThemes2 = selectedThemes;
    }

    public void setSelectedThemes(List<String> selectedThemes) {
        this.selectedThemes = selectedThemes;
    }

    public List<Ideia> getItemsToConvert() {
        return getFacade().findAll();
    }

    public void setItemsToConvert(List<Ideia> items) {
        this.itemsToConvert = items;
    }

    public Ideia getSelected() {
        return selected;
    }

    public void setSelected(Ideia c) {
        this.selected = c;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private IdeiaFacade getFacade() {
        return ejbFacade;
    }

    public List<Ideia> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Ideia> filtered) {
        this.filtered = filtered;
    }

    public List<Ideia> getFilteredSugeridas() {
        return filteredSugeridas;
    }

    public void setFilteredSugeridas(List<Ideia> filteredSugeridas) {
        this.filteredSugeridas = filteredSugeridas;
    }

    public List<Ideia> getFilteredInseridas() {
        return filteredInseridas;
    }

    public void setFilteredInseridas(List<Ideia> filteredInseridas) {
        this.filteredInseridas = filteredInseridas;
    }

    public List<Ideia> getFilteredVotos() {
        return filteredVotos;
    }

    public void setFilteredVotos(List<Ideia> filteredVotos) {
        this.filteredVotos = filteredVotos;
    }

    public List<Ideia> getFilteredAprovar() {
        return filteredAprovar;
    }

    public void setFilteredAprovar(List<Ideia> filteredAprovar) {
        this.filteredAprovar = filteredAprovar;
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
        selected = new Ideia();
        return "Create?faces-redirect=true";
    }

    public String prepareCreateFromMenu() {
        selected = new Ideia();
        return "ideia/Create?faces-redirect=true";
    }

    public String create() {
        try {

            Collection<Tag> cenas = new ArrayList<>();
            for (String t : this.selectedThemes) {
                Tag ta = null;
                ta = this.ejbFacadet.findByNome(t);
                if (ta == null) {
                    ta = new Tag();
                    ta.setNome(t);
                    this.ejbFacadet.create(ta);
                }
                cenas.add(ta);
            }

            this.selected.setDatainsercao(new Date());
            this.selected.setAprovada(false);
            FacesContext context = FacesContext.getCurrentInstance();
            UserSession us = (UserSession) context.getExternalContext().getSessionMap().get("userSession");
            this.selected.setUserOid(us.getUser());
            this.selected.setRating(0);

            this.selected.setTagCollection(cenas);
            getFacade().create(selected);

            this.selectedThemes = new ArrayList<String>();

            
            recreateModel();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeiaCreated"));
            if (us.getUser().getGroupOid().getGroupname().equals("URegistado")) {
                return "/faces/uregistado/index.xhtml?faces-redirect=true";
            }
            if (us.getUser().getGroupOid().getGroupname().equals("Moderador")) {
                return "/faces/mod/index.xhtml?faces-redirect=true";
            }
            if (us.getUser().getGroupOid().getGroupname().equals("Administrador")) {
                return "/faces/admin/index.xhtml?faces-redirect=true";
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return null;
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeiaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void update2() {

        try {
            Collection<Tag> cenas = new ArrayList<>();
            for (String t : this.selectedThemes2) {
                Tag ta = null;
                ta = this.ejbFacadet.findByNome(t);
                if (ta == null) {
                    ta = new Tag();
                    ta.setNome(t);
                    this.ejbFacadet.create(ta);
                }
                cenas.add(ta);
            }

            this.selected.setTagCollection(cenas);

            getFacade().edit(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeiaUpdated"));
            //return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            //return null;
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeiaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public DataModel getItems() {

        items = new ListDataModel(getFacade().findAll());

        return items;
    }

    public DataModel getItemsApproved() {

        this.itemsApproved = new ListDataModel(getFacade().findApproved());

        return this.itemsApproved;
    }

    public DataModel getItemsNotApproved() {

        this.itemsNotApproved = new ListDataModel(getFacade().findNotApproved());

        return this.itemsNotApproved;
    }

    private void recreateModel() {
        items = null;
    }

    public Ideia getIdeia(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter("IdeiaConverter")
    public static class IdeiaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IdeiaController controller = (IdeiaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ideiaController");
            return controller.getIdeia(getKey(value));
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
            if (object instanceof Ideia) {
                Ideia o = (Ideia) object;
                return getStringKey(o.getOid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ideia.class.getName());
            }
        }

    }

    public void aprove() {

        this.selected.setAprovada(true);
        getFacade().edit(selected);
        JsfUtil.addSuccessMessage("Ideia aprovada!");
    }

    public void unnaprove() {
        this.selected.setAprovada(false);
        getFacade().edit(selected);
        JsfUtil.addSuccessMessage("Ideia rejeitada!");
    }

    public void votar() {
        Voto v = new Voto();
        v.setIdeiaOid(selected);

        FacesContext context = FacesContext.getCurrentInstance();
        UserSession us = (UserSession) context.getExternalContext().getSessionMap().get("userSession");

        v.setUserOid(us.getUser());

        if (this.ejbVotoFacade.findVoto(v) == null) {
            this.ejbVotoFacade.create(v);
            int rating = this.selected.getRating() + 1;
            this.selected.setRating(rating);
            this.ejbFacade.edit(selected);
            JsfUtil.addSuccessMessage("Voto registado!");
        } else {
            //remover voto
            this.ejbVotoFacade.remove(this.ejbVotoFacade.findVoto(v));
            int rating = this.selected.getRating() - 1;
            this.selected.setRating(rating);
            this.ejbFacade.edit(selected);
            JsfUtil.addSuccessMessage("Voto removido!");
        }
    }

    public DataModel getUserIdeias() {

        this.userIdeias = null;

        FacesContext context = FacesContext.getCurrentInstance();
        UserSession us = (UserSession) context.getExternalContext().getSessionMap().get("userSession");

        if (this.uprofile != null) {
            System.out.println("IdeiaController getUserIdeias Entrei1");
            this.userIdeias = new ListDataModel(this.ejbFacade.findUserIdeias(this.uprofile));
            //this.uprofile = null;
        } else {
            this.userIdeias = new ListDataModel(this.ejbFacade.findUserIdeias(us.getUser()));
        }
        return this.userIdeias;
    }

    public DataModel getUserIdeiasVotou() {

        this.userIdeiasVotou = null;

        FacesContext context = FacesContext.getCurrentInstance();
        UserSession us = (UserSession) context.getExternalContext().getSessionMap().get("userSession");

        if (this.uprofile != null) {
            System.out.println("IdeiaController getUserIdeiasVotou Entrei2");
            this.userIdeiasVotou = new ListDataModel(this.ejbVotoFacade.findVotosUser(this.uprofile));
            this.uprofile = null;
        } else {
            this.userIdeiasVotou = new ListDataModel(this.ejbVotoFacade.findVotosUser(us.getUser()));
        }
        return this.userIdeiasVotou;
    }

    public boolean jaVotou(Ideia i) {
        Voto v = new Voto();
        v.setIdeiaOid(i);

        FacesContext context = FacesContext.getCurrentInstance();
        UserSession us = (UserSession) context.getExternalContext().getSessionMap().get("userSession");

        v.setUserOid(us.getUser());

        if (this.ejbVotoFacade.findVoto(v) == null) {
            return false;
        }
        return true;
    }

    public void teste(User u) {
        this.uprofile = u;
    }

}

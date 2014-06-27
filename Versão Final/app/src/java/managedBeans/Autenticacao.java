package managedBeans;

import javax.faces.context.FacesContext;

import java.util.List;
import entityClasses.User;
import facade.UserFacade;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import managedBeans.util.JsfUtil;

/**
 *
 * @author zita
 */
@ManagedBean
@Named("autenticacao")
@RequestScoped
public class Autenticacao {
    // Vars ---------------------------------------------------------------------------------------

    private static User user;
    // Properties --------------------------------------------------------------------------------
    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;
    private String username;
    private String password;
    @EJB
    private facade.UserFacade ejbFacade;

    // Actions -----------------------------------------------------------------------------------
    public void login() {
        try {
            List<User> user = ejbFacade.findToLogin(username, password);

            FacesContext context = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

            if (user != null && user.size() == 1) {

                try {
                    userSession = new UserSession();
                    User userf = (User) user.get(0);
                    // User found. Put the User in the UserSession.
                    
                    if(userf.getDataban() != null) {
                        if(userf.getDataban().before(new Date())) {
                    
                        userf.setDataultimoacesso(new Date());
                        userf.setIp(getRemoteAddr());
                        ejbFacade.edit(userf);

                        userSession.setUser(userf);
                        System.out.println(userSession.getUser().getUsername());

                        context.getExternalContext().getSessionMap().put("userSession", userSession);


                        if(userSession.getUser().getGroupOid().getGroupname().equals("Administrador"))
                            context.getExternalContext().redirect("admin/index.xhtml");
                        if(userSession.getUser().getGroupOid().getGroupname().equals("Moderador"))
                            context.getExternalContext().redirect("mod/index.xhtml");
                        if(userSession.getUser().getGroupOid().getGroupname().equals("URegistado"))
                            context.getExternalContext().redirect("uregistado/index.xhtml");
                        
                        }
                        else {
                            if(userf.getDataban().after(new Date()))
                                JsfUtil.addErrorMessage(new Exception(), "Utilizador banido! Restam "+(TimeUnit.MILLISECONDS.toDays(userf.getDataban().getTime()-(new Date().getTime())))+" dias para o ban expirar.");
                        }
                    }
                    else {
                    
                        
                        userf.setDataultimoacesso(new Date());
                        userf.setIp(getRemoteAddr());
                        ejbFacade.edit(userf);

                        userSession.setUser(userf);
                        System.out.println(userSession.getUser().getUsername());

                        context.getExternalContext().getSessionMap().put("userSession", userSession);


                        if(userSession.getUser().getGroupOid().getGroupname().equals("Administrador"))
                            context.getExternalContext().redirect("admin/index.xhtml");
                        if(userSession.getUser().getGroupOid().getGroupname().equals("Moderador"))
                            context.getExternalContext().redirect("mod/index.xhtml");
                        if(userSession.getUser().getGroupOid().getGroupname().equals("URegistado"))
                            context.getExternalContext().redirect("uregistado/index.xhtml");
                    }
                    


                } catch (IOException e) {
                    // Do your exception handling thing.
                    JsfUtil.addErrorMessage(e, ("Loading User failed." + e));
                }

            } else {
                // Do your error handling thing.
                JsfUtil.addErrorMessage(new Exception(), "nao ha user");
            }
        } catch (javax.ejb.EJBException e) {
            // Do your exception handling thing.
            JsfUtil.addErrorMessage(e, ("Loading User failed." + e.getCausedByException()));
        }

    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        // Just null out the user.
        userSession = new UserSession();
        userSession.setUser(null);
        context.getExternalContext().getSessionMap().put("userSession", userSession);
        try {
            context.getExternalContext().redirect("faces/index.xhtml");
        } catch (java.lang.Exception e) {
        }
    }

    // Getters and setters ------------------------------------------------------------------------
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Autenticacao.user = user;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UserFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    public static String getRemoteAddr() {
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
String ipAddress = request.getHeader("X-FORWARDED-FOR");
if (ipAddress == null) {
    ipAddress = request.getRemoteAddr();
}
return ipAddress;
}
}

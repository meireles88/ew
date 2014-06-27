package managedBeans;


import entityClasses.User;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
/**
 *
 * @author zita
 */
@ManagedBean
@Named("userSession")
@SessionScoped
public class UserSession  implements Serializable {

    // Properties ---------------------------------------------------------------------------------

    private String cookieId;
    private User user;
    private Date creationDate;
    private Date lastVisit;
    private int hits;

    // Constructors -------------------------------------------------------------------------------

    /**
     * Default constructor. 
     */
    public UserSession() {
        // Keep it alive.
    }

    /**
     * Construct new sessaouser with given cookie ID. 
     */
    public UserSession(String cookieId) {
        this.cookieId = cookieId;
        this.creationDate = new Date();
        this.lastVisit = new Date();
    }

    // Getters and setters ------------------------------------------------------------------------
    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Add hit (pageview) to the UserSession. Not necessary, but nice for stats.
     */
    public void addHit() {
        this.hits++;
        this.lastVisit = new Date();
    }

    /**
     * A convenience method to check if User is logged in.
     */
    public boolean isLoggedIn() {
        return user != null;
    }

}

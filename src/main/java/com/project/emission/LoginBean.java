package com.project.emission;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private boolean loggedIn = false;
    private String role;

    public String login() {
        if ("admin".equals(username) && "1234".equals(password)) {
            loggedIn = true;
            role = "scientist";  // Beispielrolle – du kannst später mehrere Rollen unterscheiden
            return "adminPanel.xhtml?faces-redirect=true";
        } else {
            loggedIn = false;
            role = null;
            return "login.xhtml?faces-redirect=true";
        }
    }

    public String logout() {
    // Sitzung invalidieren (alles zurücksetzen)
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    
    // Zusätzlich zur Sicherheit explizit Felder auf null setzen (optional aber sinnvoll)
    username = null;
    password = null;
    role = null;
    loggedIn = false;

    return "home.xhtml?faces-redirect=true";
}

    public void checkLogin() {
        if (!loggedIn) {
            FacesContext context = FacesContext.getCurrentInstance();
            try {
                context.getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkScientistAccess() {
    if (!loggedIn || !"scientist".equals(role)) {
        try {
            FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("home.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    // Getter & Setter

    public boolean isLoggedIn() {
        return loggedIn;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

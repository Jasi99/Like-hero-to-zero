package com.project.emission;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "redirect")
@RequestScoped
public class RedirectBean {

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bitte zuerst einloggen.", ""));
        context.getExternalContext().redirect("login.xhtml");
    }
}

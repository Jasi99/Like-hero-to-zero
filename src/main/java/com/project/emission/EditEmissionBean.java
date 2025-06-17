package com.project.emission;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

@ManagedBean(name = "editEmissionBean")
@ViewScoped
public class EditEmissionBean implements Serializable {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    private Integer emissionId;
    private String land;
    private int year;
    private double co2;

   @PostConstruct
public void init() {
    try {
        emf = Persistence.createEntityManagerFactory("co2emissions");
        entityManager = emf.createEntityManager();

        // ID aus URL lesen
        String idParam = javax.faces.context.FacesContext.getCurrentInstance()
            .getExternalContext().getRequestParameterMap().get("id");

        if (idParam != null && !idParam.isEmpty()) {
            emissionId = Integer.parseInt(idParam);

            EmissionRecord existing = entityManager.find(EmissionRecord.class, emissionId);
            if (existing != null) {
                this.land = existing.getLand();
                this.year = existing.getYear();
                this.co2 = existing.getCo2();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void saveEmission() {
    try {
        // EntityManagerFactory prüfen und ggf. neu erstellen
        if (emf == null) {
            System.out.println("[INFO] EntityManagerFactory ist null, wird neu erstellt.");
            emf = Persistence.createEntityManagerFactory("co2emissions");
        }

        // EntityManager prüfen und ggf. neu erstellen
        if (entityManager == null || !entityManager.isOpen()) {
            System.out.println("[INFO] EntityManager war null oder geschlossen. Neuer wird erstellt.");
            entityManager = emf.createEntityManager();
        }

        entityManager.getTransaction().begin();

        if (emissionId != null) {
            EmissionRecord existing = entityManager.find(EmissionRecord.class, emissionId);
            if (existing != null) {
                existing.setLand(land);
                existing.setYear(year);
                existing.setCo2(co2);
                entityManager.merge(existing);
            }
        } else {
            EmissionRecord newRecord = new EmissionRecord();
            newRecord.setLand(land);
            newRecord.setYear(year);
            newRecord.setCo2(co2);
            entityManager.persist(newRecord);
            System.out.println("[INFO] Emission gespeichert: " + land + " " + year + " " + co2);
        }

        entityManager.getTransaction().commit();

    } catch (Exception e) {
        e.printStackTrace();
        if (entityManager != null && entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }
}
    // Getter & Setter
    public Integer getEmissionId() { return emissionId; }
    public void setEmissionId(Integer emissionId) { this.emissionId = emissionId; }

    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getCo2() { return co2; }
    public void setCo2(double co2) { this.co2 = co2; }
}

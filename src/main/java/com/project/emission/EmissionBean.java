package com.project.emission;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "emissionBean")
@ViewScoped
public class EmissionBean implements Serializable {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    private String selectedLand;
    private String result;
    private List<EmissionRecord> allEmissions;

    @PostConstruct
    public void init() {
        try {
            emf = Persistence.createEntityManagerFactory("co2emissions");
            entityManager = emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayEmissions() {
        try {
            TypedQuery<EmissionRecord> query = entityManager.createQuery(
                    "SELECT e FROM EmissionRecord e WHERE e.land = :land", EmissionRecord.class);
            query.setParameter("land", selectedLand);
            List<EmissionRecord> records = query.getResultList();

            if (!records.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                for (EmissionRecord record : records) {
                    builder.append("Jahr: ").append(record.getYear())
                           .append(", CO2: ").append(record.getCo2()).append(" kt\n");
                }
                result = builder.toString();
            } else {
                result = "Keine Daten für das ausgewählte Land.";
            }
        } catch (Exception e) {
            result = "Fehler beim Abrufen der Daten.";
            e.printStackTrace();
        }
    }

    public List<EmissionRecord> getAllEmissions() {
        try {
            if (selectedLand != null && !selectedLand.isEmpty()) {
                TypedQuery<EmissionRecord> query = entityManager.createQuery(
                        "SELECT e FROM EmissionRecord e WHERE e.land = :land ORDER BY e.year DESC", EmissionRecord.class);
                query.setParameter("land", selectedLand);
                allEmissions = query.getResultList();

                result = allEmissions.isEmpty() ?
                        "Keine Daten für das ausgewählte Land." :
                        allEmissions.size() + " Einträge für \"" + selectedLand + "\" gefunden.";

            } else {
                TypedQuery<EmissionRecord> query = entityManager.createQuery(
                        "SELECT e FROM EmissionRecord e ORDER BY e.year DESC", EmissionRecord.class);
                allEmissions = query.getResultList();
                result = "Es wurden insgesamt " + allEmissions.size() + " Einträge gefunden.";
            }
        } catch (Exception e) {
            result = "Fehler beim Laden der Daten.";
            e.printStackTrace();
        }

        return allEmissions;
    }

    // Getter und Setter
    public String getSelectedLand() {
        return selectedLand;
    }

    public void setSelectedLand(String selectedLand) {
        this.selectedLand = selectedLand;
    }

    public String getResult() {
        return result;
    }
}

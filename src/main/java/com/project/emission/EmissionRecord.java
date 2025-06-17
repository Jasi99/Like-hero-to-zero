package com.project.emission;

import javax.persistence.*;

@Entity
@Table(name = "emission_record")
public class EmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ← AUTO für H2 empfohlen
    private Integer id;

    private String land;

    @Column(name = "emission_year")
    private int year;

    private double co2;

    // Getter und Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }
}

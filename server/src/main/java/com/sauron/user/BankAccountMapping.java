package com.sauron.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "BANK_ACCOUNT_MAPPING")
public class BankAccountMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "MARKER_COLOR", nullable = false)
    @Enumerated(EnumType.STRING)
    private Color markerColor;

    public BankAccountMapping() {
    }

    public BankAccountMapping(Long id, String name, Color markerColor) {
        this.id = id;
        this.name = name;
        this.markerColor = markerColor;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getMarkerColor() {
        return markerColor;
    }

    public void setMarkerColor(Color markerColor) {
        this.markerColor = markerColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountMapping that = (BankAccountMapping) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BankAccountMapping{" +
                "id=" + id +
                '}';
    }
}

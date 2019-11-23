package com.sauron.view;

import java.io.Serializable;

public class BankAccountView implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String color;

    public BankAccountView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

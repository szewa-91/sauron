package com.sauron.model.views;

import java.io.Serializable;

public class BankView implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String loginUrl;
    private String color;
    private String apiUrl;

    public BankView() {
    }

    public BankView(Long id, String name, String loginUrl, String color, String apiUrl) {
        this.id = id;
        this.name = name;
        this.loginUrl = loginUrl;
        this.color = color;
        this.apiUrl = apiUrl;
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

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}

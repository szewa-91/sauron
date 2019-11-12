package com.sauron.view;

import java.io.Serializable;
import java.util.Collection;

public class UserView implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    private Collection<BankView> banks;

    public UserView() {
    }

    public UserView(Long id, String username, String email, Collection<BankView> banks) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.banks = banks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<BankView> getBanks() {
        return banks;
    }

    public void setBanks(Collection<BankView> banks) {
        this.banks = banks;
    }
}

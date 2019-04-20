package com.sauron.model;

import java.util.Objects;

public class TransactionDto {

    private String name;

    public TransactionDto() {
    }

    public TransactionDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "name='" + name + '\'' +
                '}';
    }
}

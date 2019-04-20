package com.sauron.model;

import java.util.Objects;

public class TestEntityPojo {

    private String name;

    public TestEntityPojo() {
    }

    public TestEntityPojo(String name) {
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
        TestEntityPojo that = (TestEntityPojo) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TestEntityPojo{" +
                "name='" + name + '\'' +
                '}';
    }
}

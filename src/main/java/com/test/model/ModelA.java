package com.test.model;


public class ModelA {

    public ModelA(String someString) {
        this.someString = someString;
    }

    private String someString;
    public String getSomeString() {
        return someString;
    }
    public void setSomeString(String someString) {
        this.someString = someString;
    }
}

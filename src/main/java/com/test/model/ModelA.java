package com.test.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.test.view.ModelBWithModelAView;

import java.io.Serializable;

public class ModelA implements Serializable {

    public ModelA(String someString) {
        this.someString = someString;
    }

    @JsonView(ModelBWithModelAView.class)
    private String someString;
    public String getSomeString() {
        return someString;
    }
    public void setSomeString(String someString) {
        this.someString = someString;
    }
}

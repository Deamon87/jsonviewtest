package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.view.ModelBView;
import com.test.view.ModelBWithModelAView;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonView;


public class ModelB extends ModelBSuperClasss {

    @JsonView(ModelBView.class)
    private String commonField1;
    public String getCommonField1() {
        return commonField1;
    }
    public void setCommonField1(String commonField1) {
        this.commonField1 = commonField1;
    }

    @JsonView(ModelBView.class)
    private String commonField2;
    public String getCommonField2() {
        return commonField2;
    }
    public void setCommonField2(String commonField2) {
        this.commonField2 = commonField2;
    }

    @JsonIgnore
    @JsonView(ModelBWithModelAView.class)
    private List<ModelA> modelAList;
    public List<ModelA> getModelAList() {
        return modelAList;
    }
    public void setModelAList(List<ModelA> modelAList) {
        this.modelAList = modelAList;
    }
}

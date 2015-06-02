package com.test.main;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.test.model.ModelA;
import com.test.model.ModelB;
import com.test.view.ModelBWithModelAView;
import com.test.view.ModelBWithModelAViewD;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonTestMain {

    public static void main(String args[]) throws IOException, NoSuchFieldException, IllegalAccessException {

        /* 1. Create models */
        List<ModelA> modelAList = new ArrayList<ModelA>();
        modelAList.add(new ModelA("String 1"));
        modelAList.add(new ModelA("String 2"));

        ModelB modelB = new ModelB();
        modelB.setCommonField1("commonField1");
        modelB.setCommonField2("commonField2");
        modelB.setModelAList(modelAList);

        List<ModelB> modelBList = new ArrayList<ModelB>();
        modelBList.add(modelB);


        /* 2. Create object mapper */
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //objectMapper._serializationConfig._serFeatures = per features 1360828
        //objectMapper._serializationConfig._mapperFeatures = 133055

        SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
        Field _serFeatures = serializationConfig.getClass().getDeclaredField("_serFeatures");
        _serFeatures.setAccessible(true);
        _serFeatures.setInt(serializationConfig, 1360828);

        Field _mapperFeatures = serializationConfig.getClass().getSuperclass().getSuperclass().getDeclaredField("_mapperFeatures");
        _mapperFeatures.setAccessible(true);
        _mapperFeatures.setInt(serializationConfig, 133055);

        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        JsonGenerator generanor = objectMapper.getFactory().createGenerator(outputContent, JsonEncoding.UTF8);

        /* 3. Serialize with view */
        ObjectWriter objectWriter = objectMapper.writerWithView(ModelBWithModelAView.class);

        /* 3.1. Serialize a single object */
        objectWriter.writeValue(generanor, modelB);
        System.out.println("Result 1 :" + outputContent);
        outputContent.reset();

        /* 3.2. Serialize a list */
        objectWriter.writeValue(generanor, ((Object)modelBList));
        System.out.println("Result 2 :" + outputContent);
    }
}

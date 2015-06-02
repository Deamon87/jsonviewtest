package com.test.main;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.model.ModelA;
import com.test.model.ModelB;
import com.test.view.ModelBWithModelAView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.configure(SerializationFeature.WRAP_EXCEPTIONS, true);
        objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, true);
        objectMapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
        objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, true);

        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);

        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, true);
        objectMapper.configure(MapperFeature.AUTO_DETECT_CREATORS, true);
        objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
        objectMapper.configure(MapperFeature.AUTO_DETECT_GETTERS, true);
        objectMapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, true);
        objectMapper.configure(MapperFeature.AUTO_DETECT_SETTERS, true);
        objectMapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, false);
        objectMapper.configure(MapperFeature.USE_GETTERS_AS_SETTERS, true);
        objectMapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, true);
        objectMapper.configure(MapperFeature.USE_STATIC_TYPING, true);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

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

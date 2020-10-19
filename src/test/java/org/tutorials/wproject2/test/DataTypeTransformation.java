package org.tutorials.wproject2.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.runtime.TypeRegistryConfigurerSupplier;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import org.springframework.beans.factory.annotation.Configurable;

/**
import java.lang.reflect.Type;

//@Configurable
public abstract class DataTypeTransformation implements TypeRegistryConfigurerSupplier {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object from, Type to) {

        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }

}**/

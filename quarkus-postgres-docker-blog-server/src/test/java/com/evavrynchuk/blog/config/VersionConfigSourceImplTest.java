package com.evavrynchuk.blog.config;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VersionConfigSourceImplTest {

    private static final String PROPERTY_NAME = "git.branch";

    private VersionConfigSourceImpl configSource;

    @BeforeEach
    public void before() {
        configSource = new VersionConfigSourceImpl();
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals(VersionConfigSourceImpl.LOCATION, configSource.getName());
    }

    @Test
    public void testGetOrdinal() {
        Assertions.assertEquals(Integer.MAX_VALUE, configSource.getOrdinal());
    }

    @Test
    public void testGetProperties() {
        Map<String, String> properties = configSource.getProperties();
        Assertions.assertNotNull(properties);
        Assertions.assertFalse(properties.isEmpty());
        Assertions.assertTrue(properties.containsKey(PROPERTY_NAME));
    }

    @Test
    public void testGetValue() {
        Assertions.assertNotNull(configSource.getValue(PROPERTY_NAME));
    }
}

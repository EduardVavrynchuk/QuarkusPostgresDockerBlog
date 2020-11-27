package com.evavrynchuk.blog.rest.api;

import com.evavrynchuk.blog.config.ConfigProperties;
import java.util.Map;
import java.util.Optional;
import org.eclipse.microprofile.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InfoApiTest {

    private Config config;
    private InfoApi infoApi;

    @BeforeEach
    public void before() {
        config = Mockito.mock(Config.class);
        infoApi = new InfoApi(config);
    }

    @Test
    public void testGetInfo() {

        final String appVersion = "v1";
        final String tag = "t1";
        final String branch = "b1";
        final String commit = "c1";
        final String buildTimestamp = "2020-01-01T19:00:00-0500";

        Mockito
                .when(config.getValue(ConfigProperties.APP_VERSION, String.class))
                .thenReturn(appVersion);

        Mockito
                .when(config.getOptionalValue(ConfigProperties.SCM_TAG, String.class))
                .thenReturn(Optional.of(tag));

        Mockito
                .when(config.getValue(ConfigProperties.SCM_BRANCH, String.class))
                .thenReturn(branch);

        Mockito
                .when(config.getValue(ConfigProperties.SCM_COMMIT, String.class))
                .thenReturn(commit);

        Mockito
                .when(config.getValue(ConfigProperties.BUILD_TIMESTAMP, String.class))
                .thenReturn(buildTimestamp);

        Map<String, Object> info = infoApi.getInfo();

        Assertions.assertNotNull(info);
        Assertions.assertEquals(5, info.size());
        Assertions.assertEquals(appVersion, info.get(InfoApi.PROPERTY_VERSION));
        Assertions.assertEquals(tag, info.get(InfoApi.PROPERTY_TAG));
        Assertions.assertEquals(branch, info.get(InfoApi.PROPERTY_BRANCH));
        Assertions.assertEquals(commit, info.get(InfoApi.PROPERTY_COMMIT));
        Assertions.assertEquals(buildTimestamp, info.get(InfoApi.PROPERTY_BUILD_TIMESTAMP));
    }
}

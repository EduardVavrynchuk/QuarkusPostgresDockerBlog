package com.evavrynchuk.blog.rest;

import com.evavrynchuk.blog.test.LoggingUtils;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WebApplicationExceptionMapperTest {

    @BeforeAll
    public static void beforeClass() {
        LoggingUtils.initializeLogging();
    }

    private WebApplicationExceptionMapper mapper;

    @BeforeEach
    public void before() {
        mapper = new WebApplicationExceptionMapper();
    }

    @Test
    public void testToResponse_NotWrapped() {

        int responseCode = HttpServletResponse.SC_CONFLICT;

        WebApplicationException e = new WebApplicationException("test", responseCode);

        Response response = mapper.toResponse(e);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(responseCode, response.getStatus());
        Assertions.assertEquals(MediaType.TEXT_PLAIN_TYPE, response.getMediaType());
        Assertions.assertNotNull(response.getEntity());
        Assertions.assertEquals(e.getMessage(), response.readEntity(String.class));
    }

}

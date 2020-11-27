package com.evavrynchuk.blog.api;

import com.evavrynchuk.blog.model.UserResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/users")
public interface UserApi {

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses({
            @APIResponse(responseCode="200", description="Success",
                    content=@Content(mediaType=MediaType.APPLICATION_JSON, schema=@Schema(implementation=UserResponse.class))),
            @APIResponse(responseCode="400", description="Bad Request",
                    content=@Content(mediaType=MediaType.TEXT_PLAIN, schema=@Schema(implementation=String.class))),
            @APIResponse(responseCode="500", description="Unexpected Error",
                    content=@Content(mediaType=MediaType.TEXT_PLAIN, schema=@Schema(implementation=String.class)))
    })
    UserResponse addUser(
            @FormParam("username") String username
    );

}

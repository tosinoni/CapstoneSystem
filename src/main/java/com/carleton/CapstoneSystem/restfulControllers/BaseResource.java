package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.models.Program;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@RestController
@RequestMapping("/api/")
public class BaseResource {

    @GetMapping("/programs")
    public Response getAllPrograms() {
        return Response.status(Response.Status.OK).entity(Arrays.asList(Program.values())).build();
    }
}

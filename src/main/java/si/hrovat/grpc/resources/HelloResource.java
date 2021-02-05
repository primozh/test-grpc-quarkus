package si.hrovat.grpc.resources;

import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.smallrye.common.annotation.Blocking;
import si.hrovat.model.GreeterGrpc;
import si.hrovat.model.HelloRequest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloResource {

    @Inject
    @GrpcService("hello")
    GreeterGrpc.GreeterBlockingStub client;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        return Response.ok("hello").build();
    }

    @GET
    @Path("/{name}")
    @Blocking
    public Response hello(@PathParam("name") String name) {
        String message = client.sayHello(HelloRequest.newBuilder().setName(name).build()).getMessage();

        return Response.ok(message).build();
    }
}

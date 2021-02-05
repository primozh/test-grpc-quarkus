package si.hrovat.grpc.resources;

import io.smallrye.mutiny.Uni;
import si.hrovat.model.HelloReply;
import si.hrovat.model.HelloRequest;
import si.hrovat.model.MutinyGreeterGrpc;

import javax.inject.Singleton;

@Singleton
public class HelloService extends MutinyGreeterGrpc.GreeterImplBase {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item(() ->
                HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
    }
}

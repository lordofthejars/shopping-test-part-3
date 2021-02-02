package org.acme;

import javax.enterprise.event.Observes;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class ApplicationLifecycle {

    private ClientAndServer mockServer;

    void onStart(@Observes StartupEvent event) {
        mockServer = startClientAndServer(9000);

        mockServer
            .when(
                request()
                .withMethod("POST")
                .withContentType(MediaType.TEXT_PLAIN)
                .withPath("/api/discount")
            )
            .respond(
                response()
                .withStatusCode(200)
                .withContentType(MediaType.TEXT_PLAIN)
                .withBody("0.10")
            );

    }

    void onStop(@Observes ShutdownEvent event) {
        
        if (mockServer!= null) {
            this.mockServer.stop();
        }
    }
    
}
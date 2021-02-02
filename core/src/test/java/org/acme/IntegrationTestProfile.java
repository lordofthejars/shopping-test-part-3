package org.acme;

import java.util.Collections;
import java.util.List;

import io.quarkus.test.junit.QuarkusTestProfile;

public class IntegrationTestProfile implements QuarkusTestProfile {
    
    @Override
    public String getConfigProfile() { 
        return "int-test";
    }

    @Override
    public List<TestResourceEntry> testResources() { 
        return Collections.singletonList(new TestResourceEntry(InfrastructureTestResource.class));
    }

}

package org.acme;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.quarkus.test.junit.QuarkusTestProfile;

public class MyTestProfile implements QuarkusTestProfile {
    
    @Override
    public Map<String, String> getConfigOverrides() { 
        return Collections.singletonMap("greeting.message"," HW");
    }

    @Override
    public Set<Class<?>> getEnabledAlternatives() { 
        return Collections.emptySet();
    }


    @Override
    public String getConfigProfile() { 
        return "test";
    }

    @Override
    public List<TestResourceEntry> testResources() { 
        return Collections.emptyList();
    }

}

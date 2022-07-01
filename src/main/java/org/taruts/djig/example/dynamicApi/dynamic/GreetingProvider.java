package org.taruts.djig.example.dynamicApi.dynamic;

import org.taruts.djig.dynamicApi.DynamicComponent;

public interface GreetingProvider extends DynamicComponent {
    String getGreeting();
}

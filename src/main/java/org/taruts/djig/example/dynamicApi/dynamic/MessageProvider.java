package org.taruts.djig.example.dynamicApi.dynamic;

import org.taruts.djig.dynamicApi.DynamicComponent;

public interface MessageProvider extends DynamicComponent {
    String getMessage();
}

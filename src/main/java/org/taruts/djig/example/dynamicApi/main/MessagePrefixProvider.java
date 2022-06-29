package org.taruts.djig.example.dynamicApi.main;

/**
 * The main application must have a bean implementing this interface.
 * Dynamic code can use this interface to inject the bean.
 */
public interface MessagePrefixProvider {
    String getMessagePrefix();
}

package org.god.core;

import java.io.InputStream;

/**
 * @author 饺子！
 * @since 2024/7/3 14:15
 **/
public class Resources {
    public static InputStream getResourcesAsStream(String config) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
    }
}

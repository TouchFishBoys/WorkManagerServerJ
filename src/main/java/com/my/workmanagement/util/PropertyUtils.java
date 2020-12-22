package com.my.workmanagement.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

/**
 * @author jimo
 * @version 1.0.0
 */
@Component
public class PropertyUtils extends PropertySourcesPlaceholderConfigurer {

    private static PropertyResolver propertyResolver;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess, propertyResolver);
        PropertyUtils.propertyResolver = propertyResolver;
    }

    public static String getString(String key) {
        return propertyResolver.getProperty(key);
    }
}
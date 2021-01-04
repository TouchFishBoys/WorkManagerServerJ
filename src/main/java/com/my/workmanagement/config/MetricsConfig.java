package com.my.workmanagement.config;

import com.codahale.metrics.MetricRegistry;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class MetricsConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }

    @PostConstruct
    public void setUpHikariWithMetrics() {
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).setMetricRegistry(metricRegistry());
        }
    }
}

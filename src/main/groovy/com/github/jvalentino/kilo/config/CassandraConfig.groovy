package com.github.jvalentino.kilo.config

import groovy.transform.CompileDynamic
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

/**
 * I guess I need this for cassandra to work
 * @author john.valentino
 */
@CompileDynamic
@Configuration
@EnableCassandraRepositories(basePackages = 'com.github.jvalentino.kilo.repo')
class CassandraConfig {

}

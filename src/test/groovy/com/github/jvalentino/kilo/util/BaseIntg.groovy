package com.github.jvalentino.kilo.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification


/**
 * TBD
 */
@EnableAutoConfiguration(exclude=[
        CassandraDataAutoConfiguration,
        CassandraAutoConfiguration
])
@ExtendWith(SpringExtension)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:integration.properties")
abstract class BaseIntg extends Specification {

    @Autowired
    MockMvc mvc

    Object toObject(MvcResult response, Class clazz) {
        String json = response.getResponse().getContentAsString()
        new ObjectMapper().readValue(json, clazz)
    }

    String toJson(Object obj) {
        new ObjectMapper().writeValueAsString(obj)
    }

}

package com.github.jvalentino.kilo


import org.springframework.boot.SpringApplication
import spock.lang.Specification

class KiloDocAggregatorAppTest extends Specification {

    def setup() {
        GroovyMock(SpringApplication, global:true)
    }

    def "test main"() {
        when:
        KiloDocAggregatorApp.main(null)
        println new File('sample.pdf').bytes.encodeBase64()

        then:
        1 * SpringApplication.run(KiloDocAggregatorApp, null)
    }

}

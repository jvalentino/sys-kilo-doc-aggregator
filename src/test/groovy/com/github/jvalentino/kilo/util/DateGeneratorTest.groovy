package com.github.jvalentino.kilo.util

import com.github.jvalentino.kilo.util.DateGenerator
import spock.lang.Specification

class DateGeneratorTest extends Specification {

    def "Test DateGenerator"() {
        when:
        Date result = DateGenerator.date()

        then:
        result
    }
}

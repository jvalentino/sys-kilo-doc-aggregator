package com.github.jvalentino.kilo.dto

import com.github.jvalentino.kilo.entity.DocTable
import com.github.jvalentino.kilo.entity.DocVersionTable
import groovy.transform.CompileDynamic

/**
 * The result of Mockito argument capture for some reason not working in the integration
 * test. The result is that we are returning what was stored in the database so we can test it
 * @author john.valentino
 */
@CompileDynamic
class DocPair {

    DocTable docTable
    DocVersionTable docVersionTable

}

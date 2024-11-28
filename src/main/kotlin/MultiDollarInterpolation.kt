package org.kotlinlang

import kotlin.reflect.KClass

// Opt-in required: -Xmulti-dollar-interpolation

// since 2.1: $$ triggers the interpolation
val KClass<*>.jsonSchemaNew : String
    get() = $$"""
    {
      "$schema": "https://json-schema.org/draft/2020-12/schema",
      "$id": "https://example.com/product.schema.json",
      "$dynamicAnchor": "meta"
      "title": "$${simpleName ?: qualifiedName ?: "unknown"}",
      "type": "object"
    }
    """

// before 2.1: escaping the $ symbol is required to avoid interpolation
val KClass<*>.jsonSchemaOld : String
    get() = $"""
    {
      "${'$'}schema": "https://json-schema.org/draft/2020-12/schema",
      "${'$'}id": "https://example.com/product.schema.json",
      "${'$'}dynamicAnchor": "meta"
      "title": "${simpleName ?: qualifiedName ?: "unknown"}",
      "type": "object"
    }
    """

fun main() {
    data class Person(val name: String, val age: Int)

    val jsonSchema = Person::class.jsonSchemaNew
    println(jsonSchema)
}


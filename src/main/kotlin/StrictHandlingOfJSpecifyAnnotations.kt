package org.kotlinlang

import jspecify.example.SomeJavaClass


fun main() {
    val sjc = SomeJavaClass()

    sjc.foo().length

    // Raises an error in the default strict mode because the result is nullable
    // To avoid the error, use ?.length instead
    sjc.bar().length
}
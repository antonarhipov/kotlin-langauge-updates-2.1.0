package org.kotlinlang

// Opt-in requires:  -Xnon-local-break-continue

import java.io.File

// EXAMPLE 1: USE

// you start with this code, in which we gather
// the first line from a set of files
fun List<File>.readFirstLines1(): List<String> = buildList {
    for (file in this@readFirstLines1) {
        val reader = file.bufferedReader(Charsets.UTF_8)
        val line = reader.readLine()
        // here we use continue to remove the empty ones
        if (line.isNullOrEmpty()) continue
        else add(line)
    }
}

// but then we realize: hey! we miss correct closing of the file
// for this in Kotlin we use 'use', but then...
fun List<File>.readFirstLines2(): List<String> = buildList {
    for (file in this@readFirstLines2) {
        file.bufferedReader(Charsets.UTF_8).use { reader ->
            val line = reader.readLine()
            // this is now a 'continue' inside a lambda
            if (line.isNullOrEmpty()) continue
            else add(line)
        }
    }
}

// EXAMPLE 2: WITH

interface User {
    suspend fun isValid(): Boolean
}

suspend fun report1(users: List<User>) {
    for (user in users) {
        // do something with 'user'
        // ...
        // at some point
        if (!user.isValid()) break
        // continue with 'user'
    }
}

// but since we are using 'user' a lot,
// we decide to make it implicit with 'with'

suspend fun report2(users: List<User>) {
    for (user in users) {
        with (user) {
            // this is now a 'continue' inside a lambda
            if (!isValid()) break
        }
    }
}
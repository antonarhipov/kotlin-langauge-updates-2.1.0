package org.kotlinlang

// this feature is useful a lot inside the Kotlin compiler itself
// we have a lot of places references which are often generic
// but have upper bounds in functions using them

interface Reference<T> {
    val id: Int
    suspend fun get(): T?
}

abstract class Animal
data class ForestAnimal(val name: String): Animal()
sealed class FarmAnimal : Animal()
data class Cattle(val givesMilk: Boolean): FarmAnimal()
data class Sheep(val color: String): FarmAnimal()

suspend fun <T: FarmAnimal> Reference<T>.describe1(): String =
    when (val animal = get()) {
        null -> "No animal"
        is Cattle -> if (animal.givesMilk) "Cow" else "Non-cow cattle"
        is Sheep -> "Sheep of color ${animal.color}"
        // before 'else' was needed here
    }

// This is a nice example for guards too!
// The 'if' just "moves to the left"

suspend fun <T: FarmAnimal> Reference<T>.describe2(): String =
    when (val animal = get()) {
        null -> "No animal"
        is Cattle if animal.givesMilk -> "Cow"
        is Cattle -> "Non-cow cattle"
        is Sheep -> "Sheep of color ${animal.color}"
        // before 'else' was needed here
    }

// -------

// Why would you want to write <T: FarmAnimal> somewhere?
// Let's introduce another class:

sealed interface Result
data class Success(val value: String, val warnings: List<String>): Result
data class Failure(val error: String, val warnings: List<String>): Result

fun addWarning1(result: Result, warning: String): Result =
    when (result) {
        is Success -> Success(result.value, result.warnings + warning)
        is Failure -> Failure(result.error, result.warnings + warning)
    }

val resultOriginal: Success = Success("ok", emptyList())
// note that the type is a bit "lost"
val result1 = addWarning1(resultOriginal, "warning")

fun <T: Result> addWarning2(result: T, warning: String): T =
    when (result) {
        is Success -> Success(result.value, result.warnings + warning)
        is Failure -> Failure(result.error, result.warnings + warning)
    } as T  // the unchecked cast here is slightly annoying...

// Now the type is correctly "threaded" through the code
val result2 = addWarning2(resultOriginal, "warning")
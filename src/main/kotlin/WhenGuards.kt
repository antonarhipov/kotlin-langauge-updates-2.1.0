package org.kotlinlang


sealed interface Creature

data class Cthulhu(
    val madnessLevel: Int,
    val isAwakened: Boolean
) : Creature

data class Kraken(
    val length: Double,
    val isAggressive: Boolean
) : Creature



fun describeCreature(creature: Creature) =
    when (creature) {
        is Cthulhu if
        creature.isAwakened -> "Cthulhu has awakened with madness level of ${creature.madnessLevel}!"

        is Kraken if
        creature.isAggressive -> "A ${creature.length}-meter long Kraken is attacking furiously!"

        else -> "It's a cute ${creature::class.simpleName}"
    }



fun main() {
    val creatures: List<Creature> = listOf(
        Cthulhu(madnessLevel = 9001, isAwakened = true),
        Cthulhu(madnessLevel = 42, isAwakened = false),
        Kraken(length = 20.5, isAggressive = true),
        Kraken(length = 15.0, isAggressive = false)
    )

    creatures.forEach { creature ->
        println(describeCreature(creature))
    }
}


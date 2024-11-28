package org.kotlinlang

// this could be written quite similar with nesting
fun render(status: Status): String = when (status) {
    Status.Loading -> "loading"
    is Status.Ok if status.info.isEmpty() -> "no data"
    is Status.Ok -> status.info.joinToString()
    is Status.Error if status.problem == Problem.CONNECTION ->
        "problems with connection"
    is Status.Error if status.problem == Problem.AUTHENTICATION ->
        "could not be authenticated"
    else -> "unknown problem"
}

// this one is harder to write with nesting, so guards improve the readibility
fun renderWithGuards(status: Status): String = when (status) {
    Status.Loading -> "loading"
    is Status.Ok if status.info.isNotEmpty() -> status.info.joinToString()
    is Status.Error if status.isCritical -> "critical problem"
    else -> "problem, try again"
}

sealed interface Status {
    object Loading : Status
    data class Ok(val info: List<String>) : Status
    data class Error(val problem: Problem, val isCritical: Boolean = false) : Status
}

enum class Problem {
    CONNECTION, AUTHENTICATION
}


package org.kotlinlang

/**
 @SubclassOptInRequired annotation allows library authors
 to require an explicit opt-in before users can implement
 experimental interfaces or extend experimental classes.

 Useful when a library API is stable enough to use
 but might evolve with new abstract functions,
 making it unstable for inheritance
*/

// ---------- LIBRARY CODE ------------------

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "Interfaces in this library are experimental"
)
annotation class UnstableApi()

@SubclassOptInRequired(UnstableApi::class)
interface CoreLibraryApi

// -------------------------------------------



// ---------- USER CODE ----------------------

@OptIn(UnstableApi::class)
interface MyImplementation : CoreLibraryApi

// -------------------------------------------
package ademar.dps.example

import ademar.dps.Searcher

/**
 * Reduces the threshold to allow more but inaccurate results
 */
fun lowThresholdExample() {
    println("LowThresholdExample begins >>>>>>>>>>")

    data class Fruit(val name: String)

    // Create a list of fruits to we search
    val fruits = listOf(
            Fruit("Apple"),
            Fruit("Orange"),
            Fruit("Banana"),
            Fruit("Pineapple"),
            Fruit("Grape"),
            Fruit("Mango"),
            Fruit("Strawberry"),
            Fruit("Pear"),
            Fruit("Peach"),
            Fruit("Watermelon"),
            Fruit("Cherry"),
            Fruit("Lemon"),
            Fruit("Papaya"),
            Fruit("Kiwi"),
            Fruit("Avocado"),
            Fruit("Coconut"),
            Fruit("Pitaya"),
            Fruit("Carambola"),
            Fruit("Caju"))

    // Create the searcher builder
    val builder = Searcher.Builder<Fruit>()

    // Use a small threshold
    builder.threshold(0.2)

    // Add the local algorithm
    builder.local()

    // Add the global algorithm
    builder.global()

    // Add the searchables using its name to search them
    fruits.forEach { builder.searchable(it, it.name) }

    // build our instance, we use it to search ours fruits
    val searcher = builder.build()

    // Search the fruits using Pear as query
    val result = searcher.search("Pear")

    // Print our results
    result.forEachIndexed { index, fruit ->
        println("Result #$index is the fruit: $fruit")
    }

    println("LowThresholdExample ends <<<<<<<<<<<<")
}

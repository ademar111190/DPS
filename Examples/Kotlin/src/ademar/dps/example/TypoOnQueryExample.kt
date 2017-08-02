package ademar.dps.example

import ademar.dps.Searcher

/**
 * The simplest way to use the library
 */
fun typoOnQueryExample() {
    println("TypoOnQueryExample begins >>>>>>>>>>")

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

    // Add the searchables using its name to search them
    fruits.forEach { builder.searchable(it, it.name) }

    // build our instance, we use it to search ours fruits
    val searcher = builder.build()

    // Search the fruits using Peer as query, note the typo
    val result = searcher.search("Peer")

    // Print our results
    result.forEachIndexed { index, fruit ->
        println("Result #$index is the fruit: $fruit")
    }

    println("TypoOnQueryExample ends <<<<<<<<<<<<")
}

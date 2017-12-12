package ademar.dps.example

import ademar.dps.Searcher

/**
 * When you need to search an object with multiple properties
 */
fun multiplePropertiesExample() {
    println("MultiplePropertiesExample begins >>>>>>>>>>")

    data class Fruit(val name: String,
                     val scientificName: String,
                     val portugueseName: String)

    // Create a list of fruits to we search
    val fruits = listOf(
            Fruit("Apple", "Malus pumila", "Maçã"),
            Fruit("Orange", "Citrus reticulata", "Laranja"),
            Fruit("Banana", "Musa acuminata", "Banana"),
            Fruit("Pineapple", "Ananas comosus", "Abacaxi"),
            Fruit("Grape", "Vitis vinifera", "Uva"),
            Fruit("Mango", "Mangifera indica", "Manga"),
            Fruit("Strawberry", "fragaria x ananassa", "Morango"),
            Fruit("Pear", "Pyrus calleryana", "Pera"),
            Fruit("Peach", "Prunus persica", "Pêssego"),
            Fruit("Watermelon", "Citrullus lanatus", "Melancia"),
            Fruit("Cherry", "Prunus avium", "Cereja"),
            Fruit("Lemon", "Citrus × limon", "Limão"),
            Fruit("Papaya", "Carica papaya", "Mamão"),
            Fruit("Kiwi", "Actinidia deliciosa", "Quiui"),
            Fruit("Avocado", "Persea americana", "Abacate"),
            Fruit("Coconut", "Cocos nucifera", "Coco"),
            Fruit("Pitaya", "Hylocereus undatus", "Pitaia"),
            Fruit("Carambola", "Averrhoa carambola", "Carambola"),
            Fruit("Cashew", "Anacardium occidentale", "Caju"))

    // Create the searcher builder
    val builder = Searcher.Builder<Fruit>()

    // Add the local algorithm
    builder.addLocalAlgorithm()

    // Add the global algorithm
    builder.addGlobalAlgorithm()

    // Add the searchables using its name, scientific name and portuguese name to search them
    fruits.forEach {
        builder.searchable(it, it.name)
        builder.searchable(it, it.scientificName)
        builder.searchable(it, it.portugueseName)
    }

    // build our instance, we use it to search ours fruits
    val searcher = builder.build()

    // Search the fruits using Pear as query
    val result = searcher.search("Pear")

    // Print our results
    result.forEachIndexed { index, fruit ->
        println("Result #$index is the fruit: $fruit")
    }

    println("MultiplePropertiesExample ends <<<<<<<<<<<<")
}

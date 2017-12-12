package ademar.dps.example;

import ademar.dps.Searcher;

import java.util.Arrays;
import java.util.List;

/**
 * When you need to search an object with multiple properties
 */
final class MultiplePropertiesExample {

    void run() {
        System.out.println("MultiplePropertiesExample begins >>>>>>>>>>");

        // Create a list of fruits to we search
        List<Fruit> fruits = Arrays.asList(
                new Fruit("Apple", "Malus pumila", "Maçã"),
                new Fruit("Orange", "Citrus reticulata", "Laranja"),
                new Fruit("Banana", "Musa acuminata", "Banana"),
                new Fruit("Pineapple", "Ananas comosus", "Abacaxi"),
                new Fruit("Grape", "Vitis vinifera", "Uva"),
                new Fruit("Mango", "Mangifera indica", "Manga"),
                new Fruit("Strawberry", "fragaria x ananassa", "Morango"),
                new Fruit("Pear", "Pyrus calleryana", "Pera"),
                new Fruit("Peach", "Prunus persica", "Pêssego"),
                new Fruit("Watermelon", "Citrullus lanatus", "Melancia"),
                new Fruit("Cherry", "Prunus avium", "Cereja"),
                new Fruit("Lemon", "Citrus × limon", "Limão"),
                new Fruit("Papaya", "Carica papaya", "Mamão"),
                new Fruit("Kiwi", "Actinidia deliciosa", "Quiui"),
                new Fruit("Avocado", "Persea americana", "Abacate"),
                new Fruit("Coconut", "Cocos nucifera", "Coco"),
                new Fruit("Pitaya", "Hylocereus undatus", "Pitaia"),
                new Fruit("Carambola", "Averrhoa carambola", "Carambola"),
                new Fruit("Cashew", "Anacardium occidentale", "Caju"));

        // Create the searcher builder
        Searcher.Builder<Fruit> builder = new Searcher.Builder<>();

        // Add the local algorithm
        builder.addLocalAlgorithm();

        // Add the global algorithm
        builder.addGlobalAlgorithm();

        // Add the searchables using its name, scientific name and portuguese name to search them
        for (Fruit fruit : fruits) {
            builder.searchable(fruit, fruit.name);
            builder.searchable(fruit, fruit.scientificName);
            builder.searchable(fruit, fruit.portugueseName);
        }

        // build our instance, we use it to search ours fruits
        Searcher<Fruit> searcher = builder.build();

        // Search the fruits using Pear as query
        List<Fruit> result = searcher.search("Pear");

        // Print our results
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Result #" + i + " is the fruit: " + result.get(i));
        }

        System.out.println("MultiplePropertiesExample ends <<<<<<<<<<<<");
    }

    final class Fruit {

        final String name;
        final String scientificName;
        final String portugueseName;

        Fruit(String name, String scientificName, String portugueseName) {
            this.name = name;
            this.scientificName = scientificName;
            this.portugueseName = portugueseName;
        }

        @Override
        public String toString() {
            return "Fruit{" +
                    "name='" + name + '\'' +
                    ", scientificName='" + scientificName + '\'' +
                    ", portugueseName='" + portugueseName + '\'' +
                    '}';
        }

    }

}

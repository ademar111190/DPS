package ademar.dps.example;

import ademar.dps.Searcher;

import java.util.Arrays;
import java.util.List;

/**
 * The simplest way to use the library
 */
final class TypoOnQueryExample {

    void run() {
        System.out.println("TypoOnQueryExample begins >>>>>>>>>>");

        // Create a list of fruits to we search
        List<Fruit> fruits = Arrays.asList(
                new Fruit("Apple"),
                new Fruit("Orange"),
                new Fruit("Banana"),
                new Fruit("Pineapple"),
                new Fruit("Grape"),
                new Fruit("Mango"),
                new Fruit("Strawberry"),
                new Fruit("Pear"),
                new Fruit("Peach"),
                new Fruit("Watermelon"),
                new Fruit("Cherry"),
                new Fruit("Lemon"),
                new Fruit("Papaya"),
                new Fruit("Kiwi"),
                new Fruit("Avocado"),
                new Fruit("Coconut"),
                new Fruit("Pitaya"),
                new Fruit("Carambola"),
                new Fruit("Caju"));

        // Create the searcher builder
        Searcher.Builder<Fruit> builder = new Searcher.Builder<>();

        // Add the searchables using its name to search them
        for (Fruit fruit : fruits) {
            builder.searchable(fruit, fruit.name);
        }

        // build our instance, we use it to search ours fruits
        Searcher<Fruit> searcher = builder.build();

        // Search the fruits using Peer as query, note the typo
        List<Fruit> result = searcher.search("Peer");

        // Print our results
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Result #" + i + " is the fruit: " + result.get(i));
        }

        System.out.println("TypoOnQueryExample ends <<<<<<<<<<<<");
    }

    final class Fruit {

        final String name;

        Fruit(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

}

package ademar.dps.example;

import ademar.dps.Searcher;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

final class Main {

    public static void main(String[] args) throws InterruptedException {
        // Create the builder
        Searcher.Builder<Fruit> builder = new Searcher.Builder<Fruit>();

        // Add the algorithms
        builder.addLocalAlgorithm();
        builder.addGlobalAlgorithm();

        // Add the data to search
        builder.searchable(new Fruit("Apple"), "Apple");
        builder.searchable(new Fruit("Orange"), "Orange");
        builder.searchable(new Fruit("Banana"), "Banana");

        // Build the searcher
        final Searcher<Fruit> searcher = builder.build();

        // The query to search ( should find banana and orange in this order )
        final String query = "anan";

        // Uses Rx to thread
        Observable.fromCallable(() -> searcher.search(query))
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .flatMapIterable(it -> it)
                .subscribe(fruit -> {
                    // Receives the fruits, one by time
                    System.out.println("Success, item: " + fruit);
                }, error -> {
                    // Some error occurs
                    System.out.println("Error: " + error);
                }, () -> {
                    // Finish the search
                    System.out.println("Done");
                });

        // Just wait the execution
        Thread.sleep(1000L);
    }

}

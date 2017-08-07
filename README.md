Dynamic Programming Searcher
============================

Dynamic Programming Searcher is a simple way to use Dynamic Programming to match charsequences and create smart searches on Android, Kotlin and Java 6+ projects. 

[![Build Status](https://www.bitrise.io/app/ba4952e69b7697bc/status.svg?token=_9ZxPIBzu3grmyODpQuBpQ)](https://www.bitrise.io/app/ba4952e69b7697bc) [![](https://jitpack.io/v/ademar111190/dps.svg)](https://jitpack.io/#ademar111190/dps)

## How to use ##

See the examples folder for more details.

1º Add [JitPack](https://jitpack.io/) to your build file

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2º Add the gradle dependency

```
implementation 'com.github.ademar111190:dps:-SNAPSHOT'
```

3º Get a Builder instance for the class you want to search, in this example we want to search a Fruit class

```
Searcher.Builder<Fruit> builder = new Searcher.Builder<>();
```

4º Add algorithms, the library provide two (global and local) you can use it or you can implement your own.

```
builder.local();
builder.global();
builder.dynamicProgramming( _yourALgorithm_ );
```

5º Add the instaces you want to search and the text to use as term to the instance to be found

```
builder.searchable(new Orange(), "Orange");
builder.searchable(new Apple(), "Apple");
builder.searchable(new Banana(), "Banana");
```

6º Build the searcher

```
Searcher<Fruit> searcher = builder.build();
```

7º Search it

```
List<Fruit> result = searcher.search("orange");
```

## Custom usage ##

You can customize the follow arguments using the builder:

- threshold (The value to filter the searchables from 0 to 1)
- normalized (true if should normalize the string false otherwise)
- local / global (You can use both algorithms and/or provide a custom)
- dynamicProgramming (A custom imlementation of Dynamic Programming)

## Android ##

You can use the library as a normal java library or you can use a complete search here:

TODO link

## Thread ##

The search can consume more time than you can allow in your thread, to avoid it you need to run in another thread, the easy way to do it is with Rx so you can check the Rx sample to see how to do it.
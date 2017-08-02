Dynamic Programming Searcher
============================

Dynamic Programming Searcher is a simple way to use Dynamic Programming to match charsequences and create smart searches on Android, Java and Kotlin projects. 

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

4º Add the instaces you want to search and the text to use as term to the instance to be found

```
builder.searchable(new Orange(), "Orange");
builder.searchable(new Apple(), "Apple");
builder.searchable(new Banana(), "Banana");
```

5º Build the searcher

```
Searcher<Fruit> searcher = builder.build();
```

6º Search it

```
List<Fruit> result = searcher.search("orange");
```

## Custom usage ##

## Android ##

## Thread ##
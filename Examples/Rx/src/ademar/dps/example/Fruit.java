package ademar.dps.example;

public final class Fruit {

    private final String name;

    Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit(name=" + name + '}';
    }

}

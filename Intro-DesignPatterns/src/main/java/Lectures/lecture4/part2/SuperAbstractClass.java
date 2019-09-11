package Lectures.lecture4.part2;

public abstract class SuperAbstractClass {

    private String name;

    public SuperAbstractClass(String name) {
        this.name = name;
    }

    public void concreteMethod() {
        System.out.println("An abstract class can have both concrete and abstract methods, " +
                "and we can use access modifiers as we want");
    }

    public abstract void abstractMethod();
}

package Lectures.lecture4.part2;

public class AbstractIterfaceTest {

    public static void main(String[] args) {

        /*  não é possivel instanciar classes abstratas, nem interfaces

        SuperAbstractClass superAbstractClass = new SuperAbstractClass();
        Interface1 interface1 = new Interface1();*/

        ConcreteClass concreteClass = new ConcreteClass("Any name 1");

        SuperAbstractClass superAbstractClass = new ConcreteClass("Any name 2");

        SuperInterface1 superInterface1 = new ConcreteClass("Any name 3");

        ConcreteClass concreteClass1 = new ConcreteClass("Any name 4");

        System.out.println(Interface1.CONST_1);
        System.out.println(Interface2.CONST_2);
        System.out.println(Interface2.anotherVariable);

        concreteClass1.abstractMethod();
        concreteClass1.method1Interface1();
        concreteClass1.method2Interface1();
        concreteClass1.method3Interface1();
        concreteClass1.method4Interface1();
        concreteClass1.method5Interface1();

        concreteClass1.methodSuperInterface1();
        concreteClass1.methodSuperInterface2();
        concreteClass1.concreteMethod();
    }
}

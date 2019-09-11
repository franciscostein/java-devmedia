package Lectures.lecture4.part2;

public interface Interface1 extends SuperInterface1, SuperInterface2 {

    //public static final é redundante
    public static final int CONST_1 = 1;

    void method1Interface1();

    //public é redundante também
    public void method2Interface1();

    //public abstract é redundante
    public abstract void method3Interface1();

    //abstract redundante
    abstract void method4Interface1();

    //abstract public redundante
    abstract public void method5Interface1();
}

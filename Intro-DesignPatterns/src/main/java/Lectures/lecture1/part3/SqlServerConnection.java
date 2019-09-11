package Lectures.lecture1.part3;

public class SqlServerConnection implements Connection {

    @Override
    public void connect() {
        System.out.println("Connecting to SQLServer");
    }
}

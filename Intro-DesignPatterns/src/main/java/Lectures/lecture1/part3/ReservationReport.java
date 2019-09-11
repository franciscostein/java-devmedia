package Lectures.lecture1.part3;

public class ReservationReport {

    private Connection connection;

    public ReservationReport(Connection connection) {
        this.connection = connection;
    }

    public void reportGeneration() {
        connection.connect();
        System.out.println("Bussiness logic in order to generate a report");
    }
}

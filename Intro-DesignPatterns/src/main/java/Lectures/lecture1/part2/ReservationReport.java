package Lectures.lecture1.part2;

public class ReservationReport {

    private OracleConnection connection;

    public ReservationReport() {
        connection = new OracleConnection();
    }

    public void reportGeneration() {
        connection.connect();
        System.out.println("Bussiness logic in order to generate a report");
    }
}

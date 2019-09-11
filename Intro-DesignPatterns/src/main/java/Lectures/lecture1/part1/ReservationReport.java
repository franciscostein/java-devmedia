package Lectures.lecture1.part1;

public class ReservationReport {

    private MysqlConnection connection;

    public ReservationReport() {
        connection = new MysqlConnection();
    }

    public void reportGeneration() {
        connection.connect();
        System.out.println("Bussiness logic in order to generate a report");
    }
}

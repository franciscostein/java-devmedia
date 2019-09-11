package Lectures.lecture1.part2;

public class ReservationService {

    private OracleConnection connection;

    public ReservationService() {
        connection = new OracleConnection();
    }

    public void createReservation() {
        connection.connect();
        System.out.println("Bussiness logic over creation of a reservation");
    }
}

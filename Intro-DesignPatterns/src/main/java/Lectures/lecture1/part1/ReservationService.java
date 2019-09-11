package Lectures.lecture1.part1;

public class ReservationService {

    private MysqlConnection connection;

    public ReservationService() {
        connection = new MysqlConnection();
    }

    public void createReservation() {
        connection.connect();
        System.out.println("Bussiness logic over creation of a reservation");
    }
}

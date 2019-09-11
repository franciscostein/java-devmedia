package Lectures.lecture1.part3;

public class ReservationService {

    private Connection connection;

    public ReservationService(Connection connection) {
        this.connection = connection;
    }

    public void createReservation() {
        connection.connect();
        System.out.println("Bussiness logic over creation of a reservation");
    }
}

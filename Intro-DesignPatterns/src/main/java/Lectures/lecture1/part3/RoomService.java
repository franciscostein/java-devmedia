package Lectures.lecture1.part3;

public class RoomService {

    private Connection connection;

    public RoomService(Connection connection) {
        this.connection = connection;
    }

    public void roomVerification() {
        connection.connect();
        System.out.println("Bussiness logic over entity room");
    }
}

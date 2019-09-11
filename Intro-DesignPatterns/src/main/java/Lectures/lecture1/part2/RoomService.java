package Lectures.lecture1.part2;

public class RoomService {

    private OracleConnection connection;

    public RoomService() {
        connection = new OracleConnection();
    }

    public void roomVerification() {
        connection.connect();
        System.out.println("Bussiness logic over entity room");
    }
}

package Lectures.lecture1.part1;

public class RoomService {

    private MysqlConnection connection;

    public RoomService() {
        connection = new MysqlConnection();
    }

    public void roomVerification() {
        connection.connect();
        System.out.println("Bussiness logic over entity room");
    }
}

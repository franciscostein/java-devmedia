package Lectures.lecture6.part1;

public class TestAirport {

    public static void main(String[] args) {
        AirportController controller = new AirportController();
        AirportController controller1 = new AirportController();

        controller.requestPermissionToTakeOff();
        controller1.requestPermissionToTakeOff();   //deve falhar, só é permitido operações alternadas

        System.out.println("");

        controller.requestPermissionToLand();
        controller1.requestPermissionToLand();
    }
}

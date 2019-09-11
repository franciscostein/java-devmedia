package Lectures.lecture6.part2;

public class TestAirport {

    public static void main(String[] args) {
        AirportController controller = AirportController.getInstance();
        AirportController controller1 = AirportController.getInstance();

        controller.requestPermissionToTakeOff();
        controller1.requestPermissionToTakeOff();   //deve falhar, só é permitido operações alternadas

        System.out.println("");

        controller.requestPermissionToLand();
        controller1.requestPermissionToLand();      //deve falhar
    }
}

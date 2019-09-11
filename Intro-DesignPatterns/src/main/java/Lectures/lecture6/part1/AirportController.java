package Lectures.lecture6.part1;

public class AirportController {

    private boolean isAllowedToLand;
    private boolean isAllowedToTakeOff;

    public AirportController() {
        isAllowedToLand = false;
        isAllowedToTakeOff = true;
    }

    public void requestPermissionToLand() {
        if (isAllowedToLand) {
            System.out.println("Permission to land granted");
            isAllowedToLand = false;
            isAllowedToTakeOff = true;
        } else {
            System.out.println("Permission to land denied");
        }
    }

    public void requestPermissionToTakeOff() {
        if (isAllowedToTakeOff) {
            System.out.println("Permission to take off granted");
            isAllowedToLand = true;
            isAllowedToTakeOff = false;
        } else {
            System.out.println("Permission to take off denied");
        }
    }
}

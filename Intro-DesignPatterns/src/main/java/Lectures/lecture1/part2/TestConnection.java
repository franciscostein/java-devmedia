package Lectures.lecture1.part2;

public class TestConnection {

    public static void main(String[] args) {
        RoomService room = new RoomService();
        room.roomVerification();

        ReservationReport reservationReport = new ReservationReport();
        reservationReport.reportGeneration();

        ReservationService reservationService = new ReservationService();
        reservationService.createReservation();
    }
}

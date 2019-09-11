package Lectures.lecture1.part1;

public class TestConnection {

    /*
    * Está programado para aplicação, não é uma boa pratica, deve se usar programação para uma interface nesse caso
    * */
    public static void main(String[] args) {
        RoomService room = new RoomService();
        room.roomVerification();

        ReservationReport reservationReport = new ReservationReport();
        reservationReport.reportGeneration();

        ReservationService reservationService = new ReservationService();
        reservationService.createReservation();
    }
}

package Lectures.lecture1.part3;

/*
*       Usando conceitos de programar para uma interface ao inves de para aplicação
* */
public class TestConnection {

    public static void main(String[] args) {
        RoomService room = new RoomService(new SqlServerConnection());
        room.roomVerification();

        ReservationReport reservationReport = new ReservationReport(new SqlServerConnection());
        reservationReport.reportGeneration();

        ReservationService reservationService = new ReservationService(new SqlServerConnection());
        reservationService.createReservation();
    }
}

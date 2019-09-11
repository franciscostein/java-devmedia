package Lectures.lecture9.part11;

public class Rental {

    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double getSubTotal() {
        return movie.getSubTotal(daysRented);
    }

    public int getCustomerPoints() {
        return movie.getCustomerPoints(daysRented);
    }
}

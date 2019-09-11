package Lectures.lecture9.part10;

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
        double subTotal = 0;
        switch (getMovie().getMovieType()) {
            case NORMAL:
                subTotal += 2;
                if (getDaysRented() > 2) { //fined with the customer takes more than two days to return the movie
                    subTotal += (getDaysRented() - 2) * 1.5;
                }
                break;
            case RELEASES:
                subTotal += getDaysRented() * 3;  // $3 for which day of releases rent
                break;
            case CHILDREN:
                subTotal += 1.5;    //for 3 days
                if (getDaysRented() > 3) {
                    subTotal += (getDaysRented() - 3) * 1.5;
                }
        }
        return subTotal;
    }

    public int getCustomerPoints() {
        int customerPoints = 0;
        customerPoints++;

        if (getMovie().getMovieType() == MovieType.RELEASES && getDaysRented() > 1) {
            customerPoints++;
        }
        return customerPoints;
    }
}

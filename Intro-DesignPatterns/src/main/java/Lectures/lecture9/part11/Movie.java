package Lectures.lecture9.part11;

public class Movie {

    private MovieType movieType;
    private String title;

    public Movie(String title, MovieType movieType) {
        this.title = title;
        this.movieType = movieType;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public String getTitle() {
        return title;
    }

    public double getSubTotal(int daysRented) {
        double subTotal = 0;
        switch (movieType) {
            case NORMAL:
                subTotal += 2;
                if (daysRented > 2) { //fined with the customer takes more than two days to return the movie
                    subTotal += (daysRented - 2) * 1.5;
                }
                break;
            case RELEASES:
                subTotal += daysRented * 3;  // $3 for which day of releases rent
                break;
            case CHILDREN:
                subTotal += 1.5;    //for 3 days
                if (daysRented > 3) {
                    subTotal += (daysRented - 3) * 1.5;
                }
        }
        return subTotal;
    }

    public int getCustomerPoints(int daysRented) {
        int customerPoints = 0;
        customerPoints++;

        if (movieType == MovieType.RELEASES && daysRented > 1) {
            customerPoints++;
        }
        return customerPoints;
    }
}

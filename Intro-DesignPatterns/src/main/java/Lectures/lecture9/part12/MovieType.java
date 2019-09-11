package Lectures.lecture9.part12;

public enum MovieType {

    NORMAL(2), RELEASES(3), CHILDREN(1.5);

    private double price;

    MovieType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public double getSubTotal(int daysRented, MovieType movieType) {
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

    public int getCustomerPoints(int daysRented, MovieType movieType) {
        int customerPoints = 0;
        customerPoints++;

        if (movieType == MovieType.RELEASES && daysRented > 1) {
            customerPoints++;
        }
        return customerPoints;
    }
}

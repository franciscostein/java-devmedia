package Lectures.lecture4.part3;

public class SillyDate implements Cloneable {

    private int day;
    private int month;
    private int year;

    public SillyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public static void main(String[] args) {
        SillyDate sillyDate = new SillyDate(8, 12, 2018);
        System.out.println("Printing the SillyDate details:");
        System.out.println(sillyDate.toString());

        try {
            SillyDate cloneObject = (SillyDate) sillyDate.clone();
            System.out.println("Printing the cloneObject details:");
            System.out.println(cloneObject.toString());
        } catch (CloneNotSupportedException e) {
            System.out.println("\nError trying to clone class \n" + e.getMessage());
        }
    }
}

package Lectures.lecture4.part8;

public class CD {

    private String name;

    private String album;

    private String label;

    private int year;

    public CD(String name, String album, String label, int year) {
        this.name = name;
        this.album = album;
        this.label = label;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

package fisrthibernateapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "testhibernate2.CineLovers")
@PrimaryKeyJoinColumn(name = "ID_PEOPLE")
public class CineLover extends People {

    @Column
    private String bestMovie;

    public CineLover() {
    }

    public String getBestMovie() {
        return bestMovie;
    }

    public void setBestMovie(String bestMovie) {
        this.bestMovie = bestMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CineLover cineLover = (CineLover) o;

        return bestMovie.equals(cineLover.bestMovie);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + bestMovie.hashCode();
        return result;
    }
}

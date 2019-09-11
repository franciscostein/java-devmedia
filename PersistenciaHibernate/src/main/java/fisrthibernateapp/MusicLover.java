package fisrthibernateapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "testhibernate2.MusicLovers")
@PrimaryKeyJoinColumn(name = "ID_PEOPLE")
public class MusicLover extends People {

    @Column
    private String idealMusic;

    public MusicLover() {
    }

    public String getIdealMusic() {
        return idealMusic;
    }

    public void setIdealMusic(String idealMusic) {
        this.idealMusic = idealMusic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MusicLover that = (MusicLover) o;

        return idealMusic.equals(that.idealMusic);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idealMusic.hashCode();
        return result;
    }
}

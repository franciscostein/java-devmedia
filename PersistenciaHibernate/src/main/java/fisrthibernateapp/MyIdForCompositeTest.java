package fisrthibernateapp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MyIdForCompositeTest implements Serializable {

    @Column
    private int id;

    @Column
    private int seq;

    public int getId() {
        return id;
    }

    public MyIdForCompositeTest() {
    }

    public MyIdForCompositeTest(int id, int seq) {
        this.id = id;
        this.seq = seq;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}

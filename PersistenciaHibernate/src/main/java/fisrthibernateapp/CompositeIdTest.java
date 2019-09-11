package fisrthibernateapp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CompositeIdTest {

    @EmbeddedId
    private MyIdForCompositeTest id;

    @Column
    private String testName;

    public CompositeIdTest() {
    }

    public MyIdForCompositeTest getId() {
        return id;
    }

    public void setId(MyIdForCompositeTest id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}

package fisrthibernateapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "testhibernate2.NaturalPeople")
public class NaturalPerson extends Person {

    @Column
    private String cpf;

    public NaturalPerson() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NaturalPerson that = (NaturalPerson) o;

        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cpf.hashCode();
        return result;
    }
}

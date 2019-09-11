package fisrthibernateapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "testhibernate2.LegalPeople")
public class LegalPerson extends Person {

    @Column
    private String cnpj;

    public LegalPerson() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LegalPerson that = (LegalPerson) o;

        return cnpj.equals(that.cnpj);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cnpj.hashCode();
        return result;
    }
}

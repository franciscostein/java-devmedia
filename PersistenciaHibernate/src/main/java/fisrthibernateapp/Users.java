package fisrthibernateapp;

import javax.persistence.*;
import java.util.List;


//Classe pode ter nome diferente da tabela, mas tem que especificar o nome
//@Table(name = "users")
@Entity
public class Users /*implements Serializable*/ {

    //Não tenho certeza se precisa
    //private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column //Só precisa da propriedade name se o nome da variavel for diferente do banco
    private String name;

    @Version
    @Column
    private int version;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USERS_GROUPS", joinColumns = @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "ID_GROUP", referencedColumnName = "ID"))
    private List<Groups> groups;

    public Users() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        return id.equals(users.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package fisrthibernateapp;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groups {

    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USERS_GROUPS", joinColumns = @JoinColumn(name = "ID_GROUP", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "ID_USER", referencedColumnName = "ID"))
    private List<Users> users;

    public Groups() {
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

    public void setName(String name) {
        this.name = name;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Groups groups = (Groups) o;

        return id.equals(groups.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

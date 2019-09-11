package fisrthibernateapp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Products {

    @Id
    private Integer id;

    @Column(length = 150, nullable = false)
    private String name;

    //Cascade ALL tudo que fizer vai valer pra chave estrangeira, ou algum especifico tipo Remove
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Users owner;

    @Column
    private Integer stock;

    //Talvez seja productS (fiz igual ao banco, professor usou product), mas tava dando erro
    @OneToMany(mappedBy = "product")
    private List<InvoiceItens> invoiceItens;

    @Version
    @Column
    private Timestamp changedAt;

    public Products() {
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

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<InvoiceItens> getInvoiceItens() {
        return invoiceItens;
    }

    public void setInvoiceItens(List<InvoiceItens> invoiceItens) {
        this.invoiceItens = invoiceItens;
    }

    public Timestamp getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Timestamp changeAt) {
        this.changedAt = changeAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        return id.equals(products.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

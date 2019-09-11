package fisrthibernateapp;

import javax.persistence.*;

@Entity
public class InvoiceItens {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Invoice invoice;

    @ManyToOne
    @JoinColumn
    private Products product;

    @Column
    private Integer amount;

    public InvoiceItens() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceItens that = (InvoiceItens) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

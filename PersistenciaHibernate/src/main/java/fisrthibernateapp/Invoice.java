package fisrthibernateapp;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Invoice {

    @Id
    private Integer id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfSale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn //Precisa do nome se for diferente @JoinColumn(name = "vendor")
    private Users vendor;

    //fetch diz como vai ser a prioridade de carregamento, Eager ou Lazy
    //Eager carrega toda a coleção de objetos antes, pode ficar muito pesado, Lazy geralmente é a melhor opção
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InvoiceItens> itens;

    @Enumerated(EnumType.STRING)
    @Column
    private TypeOfInvoice typeOfInvoice;

    public void addItem(InvoiceItens invoiceItem) {

        if (itens == null ) {

            itens = new LinkedList<InvoiceItens>();
        }

        invoiceItem.setInvoice(this);

        itens.add(invoiceItem);
    }

    public Invoice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Users getVendor() {
        return vendor;
    }

    public void setVendor(Users vendor) {
        this.vendor = vendor;
    }

    public List<InvoiceItens> getItens() {
        return itens;
    }

    public void setItens(List<InvoiceItens> itens) {
        this.itens = itens;
    }

    public TypeOfInvoice getTypeOfInvoice() {
        return typeOfInvoice;
    }

    public void setTypeOfInvoice(TypeOfInvoice typeOfInvoice) {
        this.typeOfInvoice = typeOfInvoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        return id.equals(invoice.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

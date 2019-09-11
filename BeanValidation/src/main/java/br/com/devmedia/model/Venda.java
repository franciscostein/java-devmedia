package br.com.devmedia.model;

import br.com.devmedia.model.ValidacaoVenda.TotalPorTipo;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@TotalPorTipo
public class Venda {

    @NotNull
    @Size(min = 1)
    @Valid                              //Valid é recursivo, para cada item ele tem que validar na classe ItemVenda
    private List<ItemVenda> itens;

    @NotNull(message = "A data não pode ser nula")
    private Date data;

    @DecimalMin(value = "0", message = "O valor da venda deve ser igual ou maior que zero")
    private float total;

    @NotNull(message = "O tipo da venda não pode ser nulo")
    private TipoVenda tipo;

    public Venda() {
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public TipoVenda getTipo() {
        return tipo;
    }

    public void setTipo(TipoVenda tipo) {
        this.tipo = tipo;
    }
}

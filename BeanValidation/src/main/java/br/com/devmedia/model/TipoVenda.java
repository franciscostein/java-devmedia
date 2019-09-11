package br.com.devmedia.model;

public enum TipoVenda {

    VendaBrinde("Brinde"),
    VendaPadrao("Padrão");

    private String tipo;

    TipoVenda(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

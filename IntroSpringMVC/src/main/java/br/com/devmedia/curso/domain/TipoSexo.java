package br.com.devmedia.curso.domain;

public enum TipoSexo {

    FEMININO('F'), MASCULINO('M');

    private char desc;

    TipoSexo(char desc) {
        this.desc = desc;
    }

    public char getDesc() {
        return desc;
    }

    @Override           //Necessario nesse contexto
    public String toString() {
        return "TipoSexo{" +
                "desc=" + desc +
                '}';
    }
}

package br.com.devmedia.curso.web.conversor;

import br.com.devmedia.curso.domain.TipoSexo;
import org.springframework.core.convert.converter.Converter;

            //Esse tipo de conversor pode ser usado para converter qualquer tipo de dado, basta add no config do SpringMVC
public class TipoSexoConverter implements Converter<String, TipoSexo> {

    @Override
    public TipoSexo convert(String s) {
        char tipo = s.charAt(0);    //0 pois essa string só tem uma posição
        return tipo == TipoSexo.FEMININO.getDesc() ? TipoSexo.FEMININO : TipoSexo.MASCULINO;
    }
}

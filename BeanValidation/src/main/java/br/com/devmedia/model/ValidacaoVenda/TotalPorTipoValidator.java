package br.com.devmedia.model.ValidacaoVenda;

import br.com.devmedia.model.TipoVenda;
import br.com.devmedia.model.Venda;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TotalPorTipoValidator implements ConstraintValidator<TotalPorTipo, Venda> {

    @Override
    public void initialize(TotalPorTipo totalPorTipo) {

    }

    @Override
    public boolean isValid(Venda venda, ConstraintValidatorContext context) {

        if (venda == null) {

            return false;
        }

        boolean valido = false;

        if (venda.getTipo() == TipoVenda.VendaPadrao && venda.getTotal() > 0) {

            valido = true;
        }

        if (venda.getTipo() == TipoVenda.VendaBrinde && venda.getTotal() == 0) {

            valido = true;
        }

        if (!valido) {

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("tipo").addConstraintViolation();
        }

        return valido;
    }
}

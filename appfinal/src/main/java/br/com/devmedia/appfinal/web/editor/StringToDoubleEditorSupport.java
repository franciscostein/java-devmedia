package br.com.devmedia.appfinal.web.editor;

import org.apache.log4j.Logger;

import java.beans.PropertyEditorSupport;
import java.text.DecimalFormat;

public class StringToDoubleEditorSupport extends PropertyEditorSupport {

    private static Logger logger = Logger.getLogger(StringToDoubleEditorSupport.class);

    @Override
    public String getAsText() {

        Double value = (Double) this.getValue();

        DecimalFormat decimalFormat = new DecimalFormat("#, ##0.00");

        if (value == null) {

            value = 0.00;
        }

        return decimalFormat.format(value);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        try {

            text = text.replace(".", "").replace(",", ".");

            super.setValue(Double.parseDouble(text));

        } catch (NumberFormatException e) {

            logger.fatal("O campo salário esperava um double e recebeu uma string!", e);
        }
    }
}

package br.com.devmedia.appfinal.web.editor;

import org.apache.log4j.Logger;

import java.beans.PropertyEditorSupport;

public class StringToIntegerEditorSupport extends PropertyEditorSupport {

    private static Logger logger = Logger.getLogger(StringToIntegerEditorSupport.class);

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        try {

            super.setValue(Integer.parseInt(text));

        } catch (NumberFormatException e) {

            logger.fatal("O campo número esperava um inteiro e recebeu uma string", e);
        }
    }
}

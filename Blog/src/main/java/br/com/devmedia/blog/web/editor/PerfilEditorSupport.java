package br.com.devmedia.blog.web.editor;

import br.com.devmedia.blog.entity.Perfil;

import java.beans.PropertyEditorSupport;

public class PerfilEditorSupport extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (text.equals("ADMIN")) {

            super.setValue(Perfil.ADMIN);

        } else if (text.equals("AUTOR")) {

            super.setValue(Perfil.AUTOR);

        } else {

            super.setValue(Perfil.LEITOR);
        }
    }
}

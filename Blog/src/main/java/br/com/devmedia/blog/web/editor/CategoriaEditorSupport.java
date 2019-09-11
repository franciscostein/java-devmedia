package br.com.devmedia.blog.web.editor;

import br.com.devmedia.blog.Service.CategoriaService;
import br.com.devmedia.blog.entity.Categoria;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

public class CategoriaEditorSupport extends CustomCollectionEditor {

    private CategoriaService service;

    public CategoriaEditorSupport(Class<? extends Collection> collectionType, CategoriaService service) {
        super(collectionType);
        this.service = service;
    }

    //Cada categoria que ele encontrar vai converter em um objeto list
    @Override
    protected Object convertElement(Object element) {

        Long id = Long.valueOf((String) element);

        Categoria categoria = service.findById(id);

        return super.convertElement(categoria);
    }
}

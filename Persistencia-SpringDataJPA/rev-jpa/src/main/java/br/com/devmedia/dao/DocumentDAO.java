package br.com.devmedia.dao;

import br.com.devmedia.entity.Document;

public class DocumentDAO extends GenericDAO<Document> {

    public DocumentDAO() {
        super(Document.class);
    }
}

package br.com.devmedia.editora.controller;

import br.com.devmedia.editora.dao.EditoraDAO;
import br.com.devmedia.editora.entity.Editora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EditoraController {

    @Autowired
    private EditoraDAO editoraDAO;

    @RequestMapping("/editora/{id}/{page}")
    public Editora getEditoraWithAutor(@PathVariable Integer id, @PathVariable Integer page) {

        return editoraDAO.findEditoraWithAutores(id, page, 2);
    }

    @RequestMapping("/editora/{id}")
    public Editora getEditora(@PathVariable Integer id) {

        return editoraDAO.findById(id);
    }

    @RequestMapping("/editora")
    public Editora getEditora() {

        return editoraDAO.findById(2);
    }

    @RequestMapping("/")
    public String hello() {

        return "Hello FUCKING World!";
    }

    @RequestMapping("/editoras")
    public List<Editora> getEditoras() {

        return editoraDAO.findAll();
    }
}

package br.edu.devmedia.jdbc.dao;

import br.edu.devmedia.jdbc.exception.PersistenciaException;

import java.util.List;

public interface GenericoDAO<T> {

    void inserir(T obj) throws PersistenciaException;

    void atualizar(T obj) throws PersistenciaException;

    void deletar(Integer idPessoa) throws PersistenciaException;

    List<T> listarTodos() throws PersistenciaException;

    T buscarPorID(Integer id) throws PersistenciaException;
}

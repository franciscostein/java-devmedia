package br.com.devmedia.repository;

import br.com.devmedia.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Procedure(name = "docs.procedureReplaceCPF")
    String procedureReplaceCPF(@Param("ID_IN") Long id);

    @Procedure(procedureName = "procReplaceCPF")
    String replaceCPF(Long id);

    //Se mudar o nome da entidade pelo @Entity, exemplo @Entity("docs"), por usar o #{#entityName}, que ele pega o nome
    @Query("select d from Document d where d.cpf like :start%")
    List<Document> findByCpfStartsWith(@Param("start") String start);
}

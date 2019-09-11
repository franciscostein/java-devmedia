package br.edu.devmedia.crud.decorator;

import br.edu.devmedia.crud.dto.PessoaDTO;
import br.edu.devmedia.crud.dto.PreferenciaMusicalDTO;
import org.displaytag.decorator.TableDecorator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PessoaDecorator extends TableDecorator {

    public String getNome() {

        PessoaDTO pessoaDTO = (PessoaDTO) getCurrentRowObject();

        return pessoaDTO.getNome() + "*";
    }

    public String getCpf() {

        PessoaDTO pessoaDTO = (PessoaDTO) getCurrentRowObject();
        String cpf = pessoaDTO.getCpf();

        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 12);
    }

    public String getDtNasc() {

        PessoaDTO pessoaDTO = (PessoaDTO) getCurrentRowObject();
        String date = pessoaDTO.getDtNasc();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateFormated = null;

        try {

            dateFormated = df.parse(date);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        return df.format(dateFormated);
    }

    public String getEditar() {

        PessoaDTO pessoaDTO = (PessoaDTO) getCurrentRowObject();

        StringBuilder edit = new StringBuilder();
        edit.append("<a href='main?acao=editarPessoa&id_pessoa=")
                .append(pessoaDTO.getIdPessoa())
                .append("' title='Editar'> ")
                .append(" <img alt='Edição de Pessoa' src='img/Edit-32.png'/></a>");

        return edit.toString();
    }

    public String getRemover() {

        PessoaDTO pessoaDTO = (PessoaDTO) getCurrentRowObject();

        StringBuilder remover = new StringBuilder();
        remover.append("<a href='main?acao=removerPessoa&id_pessoa=")
                .append(pessoaDTO.getIdPessoa())
                .append("' title='Remover'> ")
                .append(" <img alt='Edição de Pessoa' src='img/delete-32.png'/></a>");

        return remover.toString();
    }

    public String getPreferencias() {

        PessoaDTO pessoaDTO = (PessoaDTO) getCurrentRowObject();

        StringBuilder preferencias = new StringBuilder();

        if (pessoaDTO.getPreferencias() != null && !pessoaDTO.getPreferencias().isEmpty()) {

            for (PreferenciaMusicalDTO p : pessoaDTO.getPreferencias()) {

                preferencias.append("[").append(p.getDescricao()).append("]");
            }

        } else {

            preferencias.append("Sem preferências");
        }

        return preferencias.toString();
    }
}

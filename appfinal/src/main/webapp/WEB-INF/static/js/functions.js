function localizarPorCargo() {

        var id = document.getElementById('cargo');

        var value = id.options[id.selectedIndex].value;

        if (value == '') {

            //window.location = 'http://localhost:8080/app-final/funcionario/add';
            window.location = 'http://localhost:8080/funcionario/add';

        } else {

            //window.location = 'http://localhost:8080/app-final/funcionario/find/cargo' + value;
            window.location = 'http://localhost:8080/funcionario/find/cargo' + value;
        }
    }

function localizarPorNome() {

    var value = document.getElementById('nome').value;

    if (value == '') {

        //window.location = 'http://localhost:8080/app-final/funcionario/add';
        window.location = 'http://localhost:8080/funcionario/add';

    } else {

        //window.location = 'http://localhost:8080/app-final/funcionario/find/nome' + value;
        window.location = 'http://localhost:8080/funcionario/find/nome' + value;
    }
}
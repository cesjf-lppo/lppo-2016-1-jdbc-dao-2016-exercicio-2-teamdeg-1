<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Estabelecimento</title>
    </head>
    <body>
        <jsp:include page="fragments/menu.jspf" />
        <h1>Novo Estabelecimento</h1>
        <form method="post">
            <div>
                <label>Funcionário:
                <input type="text" name="funcionario" />
                </label>
            </div>
            <div>
                <label>Descrição:
                    <textarea name="descricao" ></textarea>
                </label>
            </div>
            <div>
                <label>Tipo:
                <input type="text" name="tipo" />
                </label>
            </div>
            <div>
                <label>Horas:
                <input type="number" name="horas" />
                </label>
            </div>
            <div>
                <input type="submit" />
            </div>
        </form>
    </body>
</html>

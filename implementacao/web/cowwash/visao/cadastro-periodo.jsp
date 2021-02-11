<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>CowWash - Cadastro de Períodos dos Estados da Válvula </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="estilo.css">
    </head>
    <body>
        <%
            if (request.getParameter("enviar") == null) {
        %>
        <form method="POST" action="cadastro-periodo">
            Período (em minutos):
            <br>
            <input type="number" name="periodo" required>
            <br>
            Estado:
            <br>
            <select name="estado" required>
                <option value="" selected>[Selecione o Estado]</option>
                <c:forEach items="${estados}" var="estado">
                    <option value="${estado.id}">${estado.descricao}</option>
                </c:forEach>
            </select>
            <br>
            <input type="reset" value="Limpar">
            <input type="submit" name="enviar" value="Enviar">
        </form>
        <%
            }
            else {
        %>
            <h3>A seguinte configuração foi cadastrada:</h3>
            <ul>
                <li>Período: <b><c:out value="${valvula.periodo}"></c:out> minutos</b>;</li>
                <li>Estado: <b><c:out value="${valvula.estado.descricao}"></c:out></b>.</li>
            </ul>
            <a href="/valvula/cadastro-periodo">Voltar</a>
        <%
            }
        %>
    </body>
</html>
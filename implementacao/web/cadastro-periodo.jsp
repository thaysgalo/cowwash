<%@ page contentType="text/html; charset=UTF-8" %>
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
        <form method="POST">
            Período (em minutos):
            <br>
            <input type="number" name="periodo" required>
            <br>
            Estado:
            <br>
            <select name="estado" required>
                <option value="" selected>[Selecione o Estado]</option>
                <option value="Aberto">Aberto</option>
                <option value="Fechado">Fechado</option>
            </select>
            <br>
            <input type="reset" value="Limpar">
            <input type="submit" name="enviar" value="Enviar">
        </form>
        <%
            }
            else {
                String periodo = request.getParameter("periodo");
                String estado = request.getParameter("estado");
        %>
            <h3>A seguinte configuração foi cadastrada:</h3>
            <ul>
                <li>Período: <b><%= periodo %> minutos</b>;</li>
                <li>Estado: <b><%= estado %></b>.</li>
            </ul>
            <a href="<%= request.getRequestURI() %>">Voltar</a>
        <%
            }
        %>
    </body>
</html>
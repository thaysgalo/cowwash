import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class Valvula extends HttpServlet {
    public void doGet(HttpServletRequest requisicaoHTTP, HttpServletResponse respostaHTTP) throws ServletException, IOException {
        PrintWriter escritorHTTP = respostaHTTP.getWriter();
        respostaHTTP.setContentType("text/plain");
        respostaHTTP.setCharacterEncoding("UTF-8");

        // OS VALORES RETORNADOS ABAIXO ("1" E "2") DEVERÃO VIR DO BANCO DE DADOS.
        String query = requisicaoHTTP.getQueryString();
        if (query.contains("aberto"))
            escritorHTTP.print("1");
        else
            escritorHTTP.print("2");
        escritorHTTP.flush();
    }
}
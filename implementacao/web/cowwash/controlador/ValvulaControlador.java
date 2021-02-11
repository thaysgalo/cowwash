package cowwash.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import cowwash.modelo.Estado;
import cowwash.modelo.Valvula;
import cowwash.modelo.persistencia.ValvulaDao;

public class ValvulaControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest requisicaoHTTP, HttpServletResponse respostaHTTP) throws ServletException, IOException {
        PrintWriter escritorHTTP = respostaHTTP.getWriter();
        respostaHTTP.setContentType("text/plain");
        respostaHTTP.setCharacterEncoding("UTF-8");

        Valvula valvula = new ValvulaDao().obterAtual(new Estado(Integer.parseInt(requisicaoHTTP.getParameter("estado"))));
        escritorHTTP.print(valvula == null ? 0 : valvula.getPeriodo());
        escritorHTTP.flush();
    }
}
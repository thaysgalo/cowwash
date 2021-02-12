package cowwash.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import cowwash.modelo.Estado;
import cowwash.modelo.Valvula;
import cowwash.modelo.logica.ValvulaManager;
import cowwash.modelo.persistencia.EstadoDao;

public class ValvulaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORMULARIO = "/valvula/cadastro-periodo";
	private static final String SUFIXO_JSP = ".jsp";

	public void doGet(HttpServletRequest requisicaoHTTP, HttpServletResponse respostaHTTP) throws ServletException, IOException {
		processarRequisicao(requisicaoHTTP, respostaHTTP);
	}

	public void doPost(HttpServletRequest requisicaoHTTP, HttpServletResponse respostaHTTP) throws ServletException, IOException {
		String periodo = requisicaoHTTP.getParameter("periodo");
		String estado = requisicaoHTTP.getParameter("estado");
		Valvula valvula = new Valvula(Integer.parseInt(periodo), Integer.parseInt(estado));
		
		new ValvulaManager().inserir(valvula);
		requisicaoHTTP.setAttribute("valvula", valvula);

		RequestDispatcher despachoRequisicao = requisicaoHTTP.getRequestDispatcher(FORMULARIO + SUFIXO_JSP);
		despachoRequisicao.forward(requisicaoHTTP, respostaHTTP);
	}

	private void processarRequisicao(HttpServletRequest requisicaoHTTP, HttpServletResponse respostaHTTP) throws ServletException, IOException {
		String descricaoUrl = requisicaoHTTP.getServletPath();

		switch (descricaoUrl) {
			case FORMULARIO:
				List<Estado> estados = new EstadoDao().listar();

				requisicaoHTTP.setAttribute("estados", estados);

				RequestDispatcher despachoRequisicao = requisicaoHTTP.getRequestDispatcher(FORMULARIO + SUFIXO_JSP);
				despachoRequisicao.forward(requisicaoHTTP, respostaHTTP);
				break;
			default:
				PrintWriter escritorHTTP = respostaHTTP.getWriter();
				respostaHTTP.setContentType("text/plain");
				respostaHTTP.setCharacterEncoding("UTF-8");

				Valvula valvula = new ValvulaManager().obterAtual(new Estado(Integer.parseInt(requisicaoHTTP.getParameter("estado"))));
				
				escritorHTTP.print(valvula == null ? 0 : valvula.getPeriodo());
				escritorHTTP.flush();
				break;
		}
	}
}
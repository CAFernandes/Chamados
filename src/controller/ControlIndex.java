package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Graphs;
import model.Requisicao;
import model.User;
import persistence.index.indexDao;

@WebServlet({"/index"})
public class ControlIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ControlIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		indexDao iDao = new indexDao();
		User u = (User) request.getSession().getAttribute("user");
		List<Requisicao> req = iDao.listaRequisicao(u);
		List<Graphs> pizza = iDao.consultaPizza(u);
		List<Graphs> notas = iDao.constulaNotas(u);
		request.getSession().setAttribute("lReq", req);
		request.getSession().setAttribute("pizza", pizza);
		request.getSession().setAttribute("notas", notas);
		response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

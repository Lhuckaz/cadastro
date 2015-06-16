package br.com.lhuckaz.sistema.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.lhuckaz.sistema.model.Usuario;
import br.com.lhuckaz.sistema.util.Paginas;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "sistema")
public class Sistema extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// Verifica se está logado
		if (session.getAttribute("usuarioLogado") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			session.setAttribute("nome", usuario.getNome());
			req.getRequestDispatcher(Paginas.sistema()).forward(req, res);
		} else {
			session.setAttribute("msg", "Você não está logado no sistema");
			res.sendRedirect(req.getContextPath() + "/");
		}
	}

}

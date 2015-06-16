package br.com.lhuckaz.sistema.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.lhuckaz.sistema.dao.UsuarioDAO;
import br.com.lhuckaz.sistema.model.Usuario;

//@WebServlet(urlPatterns = { "/index.jsp" })
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "login")
public class IndexControler extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.getWriter().println(req.getParameter("nome") + req.getParameter("senha"));
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		Usuario usuario = null;
		try {
			usuario = new UsuarioDAO().buscaPorLoginESenha(login, senha);
		} catch (SQLException e) {
			req.getSession().setAttribute("msg", "Sem conexão com o banco. Tente novamente mais tarde");
			res.sendRedirect(req.getContextPath() + "/");
			return;
		}

		if (usuario == null) {
			req.getSession().setAttribute("msg", "Usuário ou senha inválidos");
			res.sendRedirect(req.getContextPath() + "/");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("usuarioLogado", usuario);
			res.sendRedirect(req.getContextPath() + "/sistema");
		}
	}

}

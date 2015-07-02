package br.com.lhuckaz.sistema.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lhuckaz.sistema.dao.UsuarioDAO;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "cadastro")
public class Cadastro extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		boolean cadastrou = false;
		
		try {
			cadastrou = new UsuarioDAO().cadastraUsuario(nome, login, senha);
		} catch (SQLException e) {
			req.getSession().setAttribute("msg", "Sem conexão com o banco. Tente novamente mais tarde");
			res.sendRedirect(req.getContextPath() + "/");
			return;
		}

		if (cadastrou) {
			req.getSession().setAttribute("msg", "Cadastro efetuado");
			res.sendRedirect(req.getContextPath() + "/");
		} else {
			req.getSession().setAttribute("msg", "Cadastro não efetuado. Usuário já existe");
			res.sendRedirect(req.getContextPath() + "/");
		}
	}
}

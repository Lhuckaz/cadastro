package br.com.lhuckaz.sistema.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "logout")
public class Logout extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getSession().removeAttribute("usuarioLogado");
		req.getSession().setAttribute("msg", "Voce foi deslogado!");
		res.sendRedirect(req.getContextPath() + "/");
	}
}

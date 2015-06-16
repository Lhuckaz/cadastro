package br.com.lhuckaz.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.lhuckaz.sistema.model.Usuario;
import br.com.lhuckaz.sistema.util.Criptografia;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() throws SQLException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	public Usuario buscaPorLoginESenha(String login, String senha) throws SQLException {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select nome, login, hash, salt from Usuario where login like ?");
			ps.setString(1, "%" + login + "%");
			ResultSet rs = ps.executeQuery();

			Usuario usuario = null;

			while (rs.next()) {
				usuario = new Usuario(rs.getString("nome"), rs.getString("login"), rs.getString("hash"),
						rs.getString("salt"));
			}
			ps.close();
			return validaSenha(usuario, senha);
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	private Usuario validaSenha(Usuario usuario, String senha) {
		try {
			if (usuario.getHash().equals(new Criptografia().getHash(senha, usuario.getSalt()))) {
				return usuario;
			}
		} catch (Exception e) {
		}
		return null;
	}

	public boolean cadastraUsuario(String nome, String login, String senha) {
		if (login.equals("") || senha.equals("") || nome.equals(""))
			return false;
		try {
			Criptografia criptografia = new Criptografia();
			String salt = criptografia.getSaltAleatorio();
			String hash = criptografia.getHash(senha, salt);
			Usuario novo = new Usuario(nome, login, hash, salt);

			PreparedStatement ps = this.connection
					.prepareStatement("insert into Usuario (nome, login, hash, salt) values (?,?,?,?)");
			ps.setString(1, novo.getNome());
			ps.setString(2, novo.getLogin());
			ps.setString(3, novo.getHash());
			ps.setString(4, novo.getSalt());
			ps.execute();
			ps.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

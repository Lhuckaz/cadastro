<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro</title>
</head>
<body>
Cadastro
<form method="post" action="${pageContext.request.contextPath}/cadastro">
<p>Nome: <input type="text" size="20" id="nome" name="nome" /></p>
<p>Login: <input type="text" size="20" id="login" name="login" /></p>
<p>Senha: <input type="password" size="20" id="senha" name="senha" /></p>
<input type="submit" value="Cadastrar" />
</form>
</body>
</html>
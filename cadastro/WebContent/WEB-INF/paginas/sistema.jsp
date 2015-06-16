<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Início</a><br>
Voce entrou no Sistema como <span>${nome}</span> <a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
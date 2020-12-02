<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.User"%>
<% User u = (User) request.getSession().getAttribute("LOGADO"); %>
<% switch(String.valueOf(u.getPermissao())) {
	case "G":
	%>	<jsp:include page="./gerente/menuGerente.jsp"></jsp:include>
	<%	break;
	case "R":%>
		<jsp:include page="./coordenador/menuCoordenador.jsp"></jsp:include>
	<%	break;
	case "T":
		%>	<jsp:include page="./tecnico/menuTecnico.jsp"></jsp:include>
	<%	break;
	case "C":
		%>	<jsp:include page="./cliente/menuCliente.jsp"></jsp:include>
	<%	break;
}%>

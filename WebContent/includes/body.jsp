<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.User"%>
<% User u = (User) request.getSession().getAttribute("LOGADO"); %>
<% switch(String.valueOf(u.getPermissao())) {
	case "G":
	%>	<jsp:include page="./gerente/indexGerente.jsp"></jsp:include>
	<%	break;
	case "R":%>
		<jsp:include page="./coordenador/indexCoordenador.jsp"></jsp:include>
	<%	break;
	case "T":
		%>	<jsp:include page="./tecnico/indexTecnico.jsp"></jsp:include>
	<%	break;
	case "C":
		%>	<jsp:include page="./cliente/indexCliente.jsp"></jsp:include>
	<%	break;
}%>

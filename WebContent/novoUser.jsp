<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1" import="model.User"%><%	User u = (User) request.getSession().getAttribute("LOGADO");%><!DOCTYPE html><html><head><jsp:include page="includes/head.jsp"></jsp:include></head><body>	<jsp:include page="includes/menu.jsp"></jsp:include>	<div class="card">		<div class="card-header">			<h2>Adicionar Novo Usu�rio</h2>		</div>		<div class="card-body" id="formulario">			<form action="./novoUser" class="form-horizontal" method="POST">				<div class="form-group">					<label class="col-sm-2 control-label">Nome: </label> <input						type="text" class="form-control" name="nome" autofocus required>				</div>				<div class="form-group">					<label class="col-sm-2 control-label">E-mail: </label> <input						type="email" class="form-control" name="email" required>				</div>				<div class="form-group">					<label class="col-sm-2 control-label">Password:</label> <input						class="form-control" type="password" name="password" required>				</div>				<div class="form-group">					<label class="col-sm-2 control-label">Administrador:</label> <input						class="form-control" list="typeuser" name="administrador" required>					<datalist id="typeuser">						<option value="Gerente">						<option value="Coordenandor">						<option value="Tecnico">						<option value="Cliente">					</datalist>				</div>				<div class="form-group">					<button type="submit" class="btn btn-primary btn-block">Enviar</button>				</div>			</form>		</div>	</div>	<jsp:include page="includes/footer.jsp"></jsp:include></body></html>
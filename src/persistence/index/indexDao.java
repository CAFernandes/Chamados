package persistence.index;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Camada;
import model.Graphs;
import model.Requisicao;
import model.User;
import persistence.connection.GenericDao;

public class indexDao {

	private Connection con;
	
	public indexDao() {
		try {
			setCon(new GenericDao().getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Graphs> consultaPizza(User u){
		List<Graphs>result = new ArrayList<Graphs>();
		String sql = "SELECT COUNT(id) AS pizza, situacao = 'Em Análise' FROM chamado c WHERE c.status = 'a'" + 
				" UNION " + 
				"SELECT COUNT(id) AS pizza, 'Aguardando Aprovação' FROM chamado c WHERE c.status = 'e'" + 
				" UNION " + 
				"SELECT COUNT(id) AS pizza, situacao = 'Em Espera' FROM chamado c WHERE c.status = 'p'" + 
				" UNION " + 
				"SELECT COUNT(id) AS pizza, situacao = 'Resolvido' FROM chamado c WHERE c.status = 'r'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Graphs(rs.getString("situacao"), rs.getInt("pizza")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Graphs> constulaNotas(User u){
		List<Graphs> result = new ArrayList<Graphs>();
		String sql = "SELECT COUNT(nota) as nota, valor = '0' FROM feedback f WHERE f.nota = 0 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '1' FROM feedback f WHERE f.nota = 1 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '2' FROM feedback f WHERE f.nota = 2 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '3' FROM feedback f WHERE f.nota = 3 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '4' FROM feedback f WHERE f.nota = 4 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '5' FROM feedback f WHERE f.nota = 5 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '6' FROM feedback f WHERE f.nota = 6 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '7' FROM feedback f WHERE f.nota = 7 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '8' FROM feedback f WHERE f.nota = 8 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '9' FROM feedback f WHERE f.nota = 9 " + 
					"UNION " + 
					"SELECT COUNT(nota) as nota, valor = '10' FROM feedback f WHERE f.nota = 10 " +
					"ORDER BY valor";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Graphs(rs.getString("valor"), rs.getInt("nota")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Requisicao> listaRequisicao(User u){
		List<Requisicao> list = new ArrayList<Requisicao>();
		String sql = null;
		switch (u.getPermissao()) {
		case 'G':
			sql = "SELECT * FROM fn_get_requisicoes_gerente(?)";
			break;
		case 'R':
			sql = "SELECT * FROM fn_get_requisicoes_coordenador(?)";
			break;
		case 'T':
			sql = "SELECT * FROM fn_get_requisicoes_tecnico(?)";
			break;
		case 'C':
			sql = "SELECT * FROM fn_get_requisicoes_cliente(?)";
			break;
		}
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Requisicao req = null;
				User c, t, r = null;
				Camada cat, sit = null;
				switch (u.getPermissao()) {
				case 'G':
					c = new User(rs.getInt("id_cliente"), rs.getString("cliente"));
					t = new User(rs.getInt("id_cliente"), rs.getString("cliente"));
					r = new User(rs.getInt("id_cliente"), rs.getString("cliente"));
					cat = new Camada(rs.getInt("id_categoria"), rs.getString("titulo"));
					sit = new Camada(rs.getInt("id_situcao"), rs.getString("situacao"));
					req = new Requisicao(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"), cat, 
							rs.getDate("data_cri"), rs.getDate("data_atualiza"), rs.getString("resposta"), 
							sit, c, t, r);
					break;
				case 'R':
					t = new User(rs.getInt("id_cliente"), rs.getString("cliente"));
					c = new User(rs.getInt("id_cliente"), rs.getString("cliente"));
					cat = new Camada(rs.getInt("id_categoria"), rs.getString("titulo"));
					sit = new Camada(rs.getInt("id_situcao"), rs.getString("situacao"));
					req = new Requisicao(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"), cat, 
							rs.getDate("data_cri"), rs.getDate("data_atualiza"), rs.getString("resposta"), 
							sit, c, t);
					break;
				case 'T':
					c = new User(rs.getInt("id_cliente"), rs.getString("cliente"));
					cat = new Camada(rs.getInt("id_categoria"), rs.getString("titulo"));
					sit = new Camada(rs.getInt("id_situcao"), rs.getString("situacao"));
					req = new Requisicao(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"), cat, 
							rs.getDate("data_cri"), rs.getDate("data_atualiza"), rs.getString("resposta"), 
							sit, c);
					break;
				case 'C':
					cat = new Camada(rs.getInt("id_categoria"), rs.getString("titulo"));
					sit = new Camada(rs.getInt("id_situcao"), rs.getString("situacao"));
					req = new Requisicao(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"), cat, 
							rs.getDate("data_cri"), rs.getDate("data_atualiza"), rs.getString("resposta"), 
							sit);
					break;
				}
				list.add(req);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}

package persistence.userDao;import java.sql.Connection;import java.util.List;import model.User;import persistence.GenericDAOException;public interface iUserDao {	User autenticar(User user)throws GenericDAOException;	void inserir(User user) throws GenericDAOException;	List<User> listar(String escolha) throws GenericDAOException;	//User consultar(String nome) throws GenericDAOException;	void editar(User user) throws GenericDAOException;	void setConnection(Connection connection);	}
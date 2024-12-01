package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * Classe responsável por gerenciar as operações relacionadas ao usuário.
 * Inclui métodos para conexão com o banco de dados e verificação de usuários.
 */
public class User {

    /*
     Estabelece a conexão com o banco de dados MySQL. 
     @return Objeto Connection representando a conexão com o banco de dados.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Inicializa o driver de conexão com o MySQL
            Class.forName("com.mysql.Driver.Manager").newInstance();

            // URL de conexão com o banco de dados
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            // Tratamento de exceção omitido
        }
        return conn;
    }

    // Variável para armazenar o nome do usuário
    public String nome = "";

    // Variável para armazenar o resultado da verificação do usuário
    public boolean result = false;

    /*
     Verifica se um usuário com as credenciais fornecidas existe no banco de dados.
     
     @param login Nome de usuário fornecido.
     @param senha Senha fornecida.
     @return Verdadeiro se o usuário existir e as credenciais estiverem corretas, falso caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();

        // Construção da instrução SQL para verificar o usuário
        sql += "SELECT nome FROM usuarios ";
        sql += "WHERE login = '" + login + "'";
        sql += " AND senha = '" + senha + "';";

        try {
            // Cria um objeto Statement para executar a consulta
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Verifica se há resultados e atribui valores às variáveis correspondentes
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            // Tratamento de exceção omitido
        }
        return result;
    }
}
// Fim da classe

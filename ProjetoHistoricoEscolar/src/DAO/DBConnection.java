package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL_MYSQL = "jdbc:mysql://localhost/faculdade";
    private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/faculdade", "root", "");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS Aluno(Matricula INT UNSIGNED AUTO_INCREMENT Primary key,Nome VARCHAR(100) NOT NULL,CPF VARCHAR(15) NOT NULL,DT_Nascimento DATE NOT NULL,Sexo VARCHAR(10) NOT NULL );";
        String sql2 = "CREATE TABLE IF NOT EXISTS Contato_Aluno(Email VARCHAR(100) NOT NULL,Telefone VARCHAR(25) NOT NULL,Matricula_Aluno INT UNSIGNED NOT NULL,FOREIGN KEY (Matricula_Aluno) REFERENCES Aluno(Matricula) ON DELETE CASCADE);";
        String sql3 = "CREATE TABLE IF NOT EXISTS Notas(ID MEDIUMINT(7) ZEROFILL UNSIGNED AUTO_INCREMENT Primary Key,Primeira_Unidade DECIMAL(5,2),Segunda_Unidade DECIMAL(5,2),Nota_Final DECIMAL(5,2));";
        String sql4 = "CREATE TABLE IF NOT EXISTS Professor(ID SMALLINT(5) ZEROFILL UNSIGNED AUTO_INCREMENT Primary Key,CPF VARCHAR(15) NOT NULL,Nome VARCHAR(100) NOT NULL);";
        String sql5 = "CREATE TABLE IF NOT EXISTS Disciplina(ID SMALLINT(5) ZEROFILL UNSIGNED AUTO_INCREMENT Primary Key,Nome VARCHAR(100) NOT NULL,Semestre TINYINT UNSIGNED NOT NULL);";
        String sql6 = "CREATE TABLE IF NOT EXISTS Curso(ID TINYINT(3) ZEROFILL UNSIGNED AUTO_INCREMENT Primary Key,Nome VARCHAR(100) NOT NULL);";
        String sql7 = "CREATE TABLE IF NOT EXISTS Cursa(Qtd_Faltas TINYINT NOT NULL,Situacao_Aluno ENUM('Aprovado por M\u00e9dia', 'Aprovado', 'Reprovado por M\u00e9dia', 'Reprovado por Falta', 'Reprovado', 'Cursando'),Matricula_Aluno INT UNSIGNED NOT NULL,ID_Notas MEDIUMINT(7) ZEROFILL UNSIGNED NOT NULL,ID_Disciplina SMALLINT(3) ZEROFILL UNSIGNED NOT NULL,PRIMARY KEY (Matricula_Aluno, ID_Notas, ID_Disciplina),FOREIGN KEY (Matricula_Aluno) REFERENCES Aluno(Matricula) ON DELETE CASCADE,FOREIGN KEY (ID_Notas) REFERENCES Notas(ID) ON DELETE CASCADE, FOREIGN KEY (ID_Disciplina) REFERENCES Disciplina(ID));";
        String sql8 = "CREATE TABLE IF NOT EXISTS Leciona(ID_Disciplina SMALLINT(5) ZEROFILL UNSIGNED NOT NULL, ID_Professor SMALLINT(5) ZEROFILL UNSIGNED NOT NULL,PRIMARY KEY (ID_Disciplina, ID_Professor),FOREIGN KEY (ID_Disciplina) REFERENCES Disciplina(ID) ON DELETE CASCADE,FOREIGN KEY (ID_Professor) REFERENCES Professor(ID) ON DELETE CASCADE);";
        String sql9 = "CREATE TABLE IF NOT EXISTS Curso_Disciplina(ID_Curso TINYINT(3) ZEROFILL UNSIGNED NOT NULL,ID_Disciplina SMALLINT(5) ZEROFILL UNSIGNED NOT NULL,PRIMARY KEY (ID_Curso, ID_Disciplina),FOREIGN KEY (ID_Curso) REFERENCES Curso(ID) ON DELETE CASCADE,FOREIGN KEY (ID_Disciplina) REFERENCES Disciplina(ID) ON DELETE CASCADE);";
        try {
            try {
                stmt = connection.prepareStatement(sql);
                stmt.execute();
                stmt = connection.prepareStatement(sql2);
                stmt.execute();
                stmt = connection.prepareStatement(sql3);
                stmt.execute();
                stmt = connection.prepareStatement(sql4);
                stmt.execute();
                stmt = connection.prepareStatement(sql5);
                stmt.execute();
                stmt = connection.prepareStatement(sql6);
                stmt.execute();
                stmt = connection.prepareStatement(sql7);
                stmt.execute();
                stmt = connection.prepareStatement(sql8);
                stmt.execute();
                stmt = connection.prepareStatement(sql9);
                stmt.execute();
                System.out.println("Tables created with success!");
            }
            catch (SQLException e) {
                e.printStackTrace();
                DBConnection.close(connection, stmt, null);
            }
        }
        finally {
            DBConnection.close(connection, stmt, null);
        }
    }
}
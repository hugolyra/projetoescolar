package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Aluno;
import Entity.Contato;
import Entity.Cursa;
import Entity.Curso;
import Entity.Curso_Disciplina;
import Entity.Disciplina;
import Entity.Leciona;
import Entity.Notas;
import Entity.Professor;

public class HistoricoEscolarDAO implements IHistoricoEscolarDAO {

	private static final String SQL_INSERT_ALUNO =
			"INSERT INTO ALUNO (NOME, CPF, DTNASCIMENTO, SEXO, TELEFONE, EMAIL)"
		  + "VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_INSERT_CONTATO =
			"INSERT INTO CONTATO_ALUNO (EMAIL, TELEFONE, MATRICULA_ALUNO)"
		  + "VALUES (?, ?, ?)";
	
	private static final String SQL_INSERT_CURSA =
			"INSERT INTO CURSA (QTD_FALTAS, SITUACAO_ALUNO, MATRICULA_ALUNO, ID_NOTAS, ID_DISCIPLINA)"
		  + "VALUES (?, ?, ?, ?, ?)";
	
	private static final String SQL_INSERT_CURSO_DISCIPLINA =
			"INSERT INTO CURSO_DISCIPLINA (ID_CURSO, ID_DISCIPLINA)"
		  + "VALUES (?, ?)";
	
	private static final String SQL_INSERT_CURSO =
			"INSERT INTO CURSO (NOME)"
		  + "VALUES (?)";
	
	private static final String SQL_INSERT_DISCIPLINA =
			"INSERT INTO DISCIPLINA (NOME, SEMESTRE)"
		  + "VALUES (?, ?)";
	
	private static final String SQL_INSERT_LECIONA =
			"INSERT INTO LECIONA (ID_DISCIPLINA, ID_PROFESSOR)"
		  + "VALUES (?, ?)";
	
	private static final String SQL_INSERT_NOTAS = 
			"INSERT INTO NOTAS (PRIMEIRA_UNIDADE, SEGUNDA_UNIDADE, NOTA_FINAL)"
		  +	"VALUES (?, ?, ?)";
	
	private static final String SQL_INSERT_PROFESSOR =
			"INSERT INTO PROFESSOR (CPF, NOME)"
		  + "VALUES (?, ?)";

	private static final String SQL_UPDATE_ALUNO =
			"UPDATE ALUNO SET NOME = ?, CPF = ?, DTNASCIMENTO = ?, SEXO = ?, EMAIL = ?, TELEFONE = ?"
		  + "WHERE MATRICULA = ?";
	
	private static final String SQL_UPDATE_CONTATO =
			"UPDATE CONTATO_ALUNO SET EMAIL = ?, TELEFONE = ?"
		  + "WHERE MATRICULA_ALUNO = ?";
	
	private static final String SQL_UPDATE_NOTAS =
			"UPDATE NOTAS SET PRIMEIRA_UNIDADE = ?, SEGUNDA_UNIDADE = ?, NOTA_FINAL = ?"
		  + "WHERE ID = ?";
	
	private static final String SQL_UPDATE_PROFESSOR =
			"UPDATE PROFESSOR SET CPF = ?, NOME = ?"
		  + "WHERE ID = ?";
	
	private static final String SQL_UPDATE_DISCIPLINA = 
			"UPDATE DISCIPLINA SET NOME = ?, SEMESTRE = ?"
		  + "WHERE ID = ?";
	
	private static final String SQL_UPDATE_CURSO = 
			"UPDATE CURSO SET NOME = ?"
		  + "WHERE ID = ?";
	
	private static final String SQL_UPDATE_CURSA =
			"UPDATE CURSA SET QTD_FALTAS = ?, SITUACAO_ALUNO = ?, ID_NOTAS = ?, ID_DISCIPLINA = ?"
		  + "WHERE MATRICULA_ALUNO = ?";
					
    private static final String SQL_REMOVE_ALUNO =
    		"DELETE FROM ALUNO WHERE MATRICULA = ?";
					
    private static final String SQL_REMOVE_CURSO =
    		"DELETE FROM CURSO WHERE ID = ?";
    
    private static final String SQL_REMOVE_DISCIPLINA =
    		"DELETE FROM DISCIPLINA WHERE ID = ?";
    		
    private static final String SQL_REMOVE_PROFESSOR =
    		"DELETE FROM PROFESSOR WHERE ID = ?";
    
    private static final String SQL_REMOVE_NOTAS =
    		"DELETE FROM NOTAS WHERE ID = ?";
    
    private static final String SQL_FIND_ALL_CURSO =
    		"SELECT * FROM CURSO";
    
    private static final String SQL_FIND_ALL_PROFESSOR =
    		"SELECT * FROM PROFESSOR";
    
    private static final String SQL_FIND_ALL_DISCIPLINA =
    		"SELECT * FROM DISCIPLINA";
    
    private static final String SQL_FIND_ALL_ALUNO =
    		"SELECT * FROM ALUNO";
    
    private static final String SQL_FIND_ALL_NOTAS = 
    		"SELECT * FROM NOTAS";
    
    private static final String SQL_FIND_VISAOALUNO = // Visão na tela do aluno
    		"SELECT DISTINCT d.Nome, p.Nome, c.Qtd_Faltas, n.Primeira_Unidade, n.Segunda_Unidade, "
    	  + "n.Nota_Final, c.Situacao_Aluno"
    	  + "FROM Aluno a, Disciplina d, Cursa c, Professor p, Notas n "
    	  + "WHERE c.Matricula_Aluno = ?"
    	  + "AND c.ID_Disciplina = d.ID"
    	  + "AND c.ID_Notas = n.ID"
    	  + "AND d.ID_Professor = p.ID";
    
    private static final String SQL_FIND_ALL_ALUNOS_DISCIPLINA = // Todos os alunos pertencentes a uma disciplina
    		"SELECT DISTINCT a.Nome, c.Qtd_Faltas, n.Primeira_Unidade, n.Segunda_Unidade, n.Nota_Final'"
    	  + "FROM Aluno a, Cursa c, Notas n, Disciplina d"
    	  + "WHERE c.ID_Disciplina = ?"
    	  + "AND c.ID_Notas = n.ID"
    	  + "AND c.Matricula_Aluno = a.Matricula";
		  
	private static final String SQL_FIND_ALL_DISCIPLINAS = // Todas as disciplinas de um professor
			"SELECT DISTINCT d.Nome as 'Disciplina', d.Semestre"
		  + "FROM Disciplina d, Leciona l, Professor p"
		  + "WHERE d.ID = l.ID_Disciplina"
		  + "AND l.ID_Professor = ?"
		  + "ORDER BY d.Semestre";

	@Override
	public int saveAluno(Aluno aluno) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_ALUNO);
		    pstm.setString(1, aluno.getNome());
		    pstm.setString(2, aluno.getCpf());
		    pstm.setDate(3, aluno.getDtNascimento());
		    pstm.setString(4, aluno.getSexo());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveContato(Contato contato) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_CONTATO);
		    pstm.setString(1, contato.getEmail());
		    pstm.setString(2, contato.getTelefone());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveCursa(Cursa cursa) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_CURSA);
		    pstm.setInt(1, cursa.getQtdFaltas());
		    pstm.setString(2, cursa.getSituacaoAluno());
		    pstm.setLong(3, cursa.getMatriculaAluno().getMatricula());
		    pstm.setLong(4, cursa.getIdNotas().getId());
		    pstm.setLong(5, cursa.getIdDisciplina().getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveCurso_Disciplina(Curso_Disciplina curso_disciplina) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_CURSO_DISCIPLINA);
		    pstm.setLong(1, curso_disciplina.getIdCurso().getId());
		    pstm.setLong(2, curso_disciplina.getIdDisciplina().getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveCurso(Curso curso) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_CURSO);
		    pstm.setString(1, curso.getNome());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   System.out.println("SQL Exception Encontrada");
		       e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveDisciplina(Disciplina disciplina) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_DISCIPLINA);
		    pstm.setString(1, disciplina.getNome());
		    pstm.setInt(2, disciplina.getSemestre());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveLeciona(Leciona leciona) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_LECIONA);
		    pstm.setLong(1, leciona.getIdDisciplina().getId());
		    pstm.setLong(2, leciona.getIdProfessor().getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveNotas(Notas notas) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_NOTAS);
		    pstm.setFloat(1, notas.getPrimeiraUnidade());
		    pstm.setFloat(2, notas.getSegundaUnidade());
		    pstm.setFloat(3, notas.getNotaFinal());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int saveProfessor(Professor professor) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_INSERT_PROFESSOR);
		    pstm.setString(1, professor.getCpf());
		    pstm.setString(2, professor.getNome());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int updateAluno(Aluno aluno) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_UPDATE_ALUNO);
		    pstm.setString(1, aluno.getNome());
		    pstm.setString(2, aluno.getCpf());
		    pstm.setDate(3, aluno.getDtNascimento());
		    pstm.setString(4, aluno.getSexo());
		    pstm.setLong(5, aluno.getMatricula());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int updateContato(Contato contato) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_UPDATE_CONTATO);
		    pstm.setString(1, contato.getEmail());
		    pstm.setString(2, contato.getTelefone());
		    pstm.setLong(3, contato.getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int updateNotas(Notas notas) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_UPDATE_NOTAS);
		    pstm.setFloat(1, notas.getPrimeiraUnidade());
		    pstm.setFloat(2, notas.getSegundaUnidade());
		    pstm.setFloat(3, notas.getNotaFinal());
		    pstm.setLong(4, notas.getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int updateProfessor(Professor professor) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_UPDATE_PROFESSOR);
		    pstm.setString(1, professor.getCpf());
		    pstm.setString(2, professor.getNome());
		    pstm.setLong(3, professor.getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int updateDisciplina(Disciplina disciplina) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_UPDATE_DISCIPLINA);
		    pstm.setString(1, disciplina.getNome());
		    pstm.setInt(2, disciplina.getSemestre());
		    pstm.setLong(3, disciplina.getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int updateCurso(Curso curso) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_UPDATE_CURSO);
		    pstm.setString(1, curso.getNome());
		    pstm.setLong(2, curso.getId());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int updateCursa(Cursa cursa) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_UPDATE_CURSO);
		    pstm.setInt(1, cursa.getQtdFaltas());
		    pstm.setString(2, cursa.getSituacaoAluno());
		    pstm.setLong(3, cursa.getIdNotas().getId());
		    pstm.setLong(4, cursa.getIdDisciplina().getId());
		    pstm.setLong(5, cursa.getMatriculaAluno().getMatricula());
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int removeAluno(Long matricula) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_REMOVE_ALUNO);
		    pstm.setLong(1, matricula);
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int removeCurso(Long id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_REMOVE_CURSO);
		    pstm.setLong(1, id);
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int removeDisciplina(Long id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_REMOVE_DISCIPLINA);
		    pstm.setLong(1, id);
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int removeProfessor(Long id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_REMOVE_PROFESSOR);
		    pstm.setLong(1, id);
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public int removeNotas(Long id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
		    pstm = conn.prepareStatement(SQL_REMOVE_NOTAS);
		    pstm.setLong(1, id);
		    result = pstm.executeUpdate();
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, null);
		    }
		    e.printStackTrace();
	    }
		return result;
	}

	@Override
	public List<Aluno> findAllAluno() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		List<Aluno> alunos = new ArrayList<Aluno>();
		ResultSet rs = null;
		try {
		    pstm = conn.prepareStatement(SQL_FIND_ALL_ALUNO);
		    rs = pstm.executeQuery();
		    
		    while(rs.next()){
		    	Aluno aluno = new Aluno();
		    	aluno.setMatricula(rs.getLong("MATRICULA"));
		    	aluno.setNome(rs.getString("NOME"));
		    	aluno.setCpf(rs.getString("CPF"));
		    	aluno.setDtNascimento(rs.getDate("DT_NASCIMENTO"));
		    	aluno.setSexo(rs.getString("SEXO"));
		    	
		    	alunos.add(aluno);
		    }
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, rs);
		    }
		    e.printStackTrace();
	    }
		return alunos;
	}

	@Override
	public List<Professor> findAllProfessor() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		List<Professor> professores = new ArrayList<Professor>();
		ResultSet rs = null;
		try {
		    pstm = conn.prepareStatement(SQL_FIND_ALL_PROFESSOR);
		    rs = pstm.executeQuery();
		    
		    while(rs.next()){
		    	Professor professor = new Professor();
		    	professor.setId(rs.getLong("ID"));
		    	professor.setCpf(rs.getString("CPF"));
		    	professor.setNome(rs.getString("NOME"));
		    	
		    	professores.add(professor);
		    }
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, rs);
		    }
		    e.printStackTrace();
	    }
		return professores;
	}

	@Override
	public List<Curso> findAllCurso() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		List<Curso> cursos = new ArrayList<Curso>();
		ResultSet rs = null;
		try {
		    pstm = conn.prepareStatement(SQL_FIND_ALL_CURSO);
		    rs = pstm.executeQuery();
		    
		    while(rs.next()){
		    	Curso curso = new Curso();
		    	curso.setId(rs.getLong("ID"));
		    	curso.setNome(rs.getString("NOME"));
		    	
		    	cursos.add(curso);
		    }
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, rs);
		    }
		    e.printStackTrace();
	    }
		return cursos;
	}

	@Override
	public List<Disciplina> findAllDisciplina() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		ResultSet rs = null;
		try {
		    pstm = conn.prepareStatement(SQL_FIND_ALL_DISCIPLINA);
		    rs = pstm.executeQuery();
		    
		    while(rs.next()){
		    	Disciplina disciplina = new Disciplina();
		    	disciplina.setId(rs.getLong("ID"));
		    	disciplina.setNome(rs.getString("NOME"));
		    	disciplina.setSemestre(rs.getInt("SEMESTRE"));
		    	
		    	disciplinas.add(disciplina);
		    }
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, rs);
		    }
		    e.printStackTrace();
	    }
		return disciplinas;
	}


	@Override
	public List<Notas> findAllNotas() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		List<Notas> notas2 = new ArrayList<Notas>();
		ResultSet rs = null;
		try {
		    pstm = conn.prepareStatement(SQL_FIND_ALL_NOTAS);
		    rs = pstm.executeQuery();
		    
		    while(rs.next()){
		    	Notas notas = new Notas();
		    	notas.setId(rs.getLong("ID"));
		    	notas.setPrimeiraUnidade(rs.getFloat("PRIMEIRA_UNIDADE"));
		    	notas.setSegundaUnidade(rs.getFloat("SEGUNDA_UNIDADE"));
		    	notas.setNotaFinal(rs.getFloat("NOTA_FINAL"));
		    	
		    	notas2.add(notas);
		    }
		} catch (SQLException e){
			try {
				if (conn != null){
				    conn.rollback();
				}
		    } catch (SQLException e1){
			   e1.printStackTrace();
		    } finally {
		    	DBConnection.close(conn, pstm, rs);
		    }
		    e.printStackTrace();
	    }
		return notas2;
	}
}

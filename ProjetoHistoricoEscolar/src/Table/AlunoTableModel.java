package Table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Aluno;

public class AlunoTableModel extends AbstractTableModel {

	private static final int COL_MATRICULA = 0;
	private static final int COL_NOME = 1;
	private static final int COL_CPF = 2;
	private static final int COL_DTNASCIMENTO = 3;
	private static final int COL_SEXO = 4;
	private static final int COL_CONTATO = 5;
	
	private List<Aluno> valores;
	
	public AlunoTableModel (List<Aluno> valores){
		this.valores = valores;
	}
	
	@Override
	public int getRowCount() {
		return valores.size();
	}
	
	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Aluno aluno = valores.get(rowIndex);
		if (columnIndex == COL_MATRICULA){
			return aluno.getMatricula();
		}else if (columnIndex == COL_NOME){
			return aluno.getNome();
		}else if (columnIndex == COL_CPF){
			return aluno.getCpf();
		}else if (columnIndex == COL_DTNASCIMENTO){
			return aluno.getDtNascimento();
		}else if (columnIndex == COL_SEXO){
			return aluno.getSexo();
		}else if (columnIndex == COL_CONTATO){
			return aluno.getContato();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column){
		String coluna = "";
		switch (column){
		    case COL_MATRICULA:
			    coluna = "Matrícula";
		        break;
		    case COL_NOME:
			    coluna = "Nome do Aluno";
		        break;
	        case COL_CPF:
		        coluna = "CPF";
	            break;
	        case COL_DTNASCIMENTO:
	    	    coluna = "Data de Nascimento";
		        break;
	        case COL_SEXO:
	    	    coluna = "Sexo";
		        break;
	        case COL_CONTATO:
	    	    coluna = "Contato";
		        break;
		    default:
			    throw new IllegalArgumentException("Coluna inválida");
		}
		return coluna;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		if(columnIndex == COL_MATRICULA){
			return Long.class;
		}else if (columnIndex == COL_NOME){
			return String.class;
		}else if (columnIndex == COL_CPF){
			return String.class;
		}else if (columnIndex == COL_DTNASCIMENTO){
			return String.class;
		}else if (columnIndex == COL_SEXO){
			return String.class;
		}else if (columnIndex == COL_CONTATO){
			return String.class;
		}
		return null;
	}
	
	public Aluno get(int row){
		return valores.get(row);
	}

}

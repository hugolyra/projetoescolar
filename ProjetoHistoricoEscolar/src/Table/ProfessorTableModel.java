package Table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Professor;

public class ProfessorTableModel extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_CPF = 1;
	private static final int COL_NOME = 2;

	private List<Professor> valores;
	
	public ProfessorTableModel (List<Professor> valores){
		this.valores = valores;
	}
	
	@Override
	public int getRowCount() {
		return valores.size();
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Professor professor = valores.get(rowIndex);
		if (columnIndex == COL_ID){
			return professor.getId();
		}else if (columnIndex == COL_CPF){
			return professor.getCpf();
		}else if (columnIndex == COL_NOME){
			return professor.getNome();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column){
		String coluna = "";
		switch (column){
		    case COL_ID:
			    coluna = "Código";
		        break;
		    case COL_CPF:
			    coluna = "CPF do Professor";
		        break;
	        case COL_NOME:
		        coluna = "Nome do Professor";
	            break;
		    default:
			    throw new IllegalArgumentException("Coluna inválida");
		}
		return coluna;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		if(columnIndex == COL_ID){
			return Long.class;
		}else if (columnIndex == COL_CPF){
			return String.class;
		}else if (columnIndex == COL_NOME){
			return String.class;
		}
		return null;
	}
	
	public Professor get(int row){
		return valores.get(row);
	}

}


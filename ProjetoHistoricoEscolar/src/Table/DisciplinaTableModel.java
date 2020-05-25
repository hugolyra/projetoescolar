package Table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Disciplina;

public class DisciplinaTableModel extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;
	private static final int COL_SEMESTRE = 2;
	
	private List<Disciplina> valores;
	
	public DisciplinaTableModel (List<Disciplina> valores){
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
		Disciplina disciplina = valores.get(rowIndex);
		if (columnIndex == COL_ID){
			return disciplina.getId();
		}else if (columnIndex == COL_NOME){
			return disciplina.getNome();
		}else if (columnIndex == COL_SEMESTRE){
			return disciplina.getSemestre();
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
		    case COL_NOME:
			    coluna = "Nome da Disciplina";
		        break;
	        case COL_SEMESTRE:
		        coluna = "Semestre";
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
		}else if (columnIndex == COL_NOME){
			return String.class;
		}else if (columnIndex == COL_SEMESTRE){
			return String.class;
		}
		return null;
	}
	
	public Disciplina get(int row){
		return valores.get(row);
	}

}

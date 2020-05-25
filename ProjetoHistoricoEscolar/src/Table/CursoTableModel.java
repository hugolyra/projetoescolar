package Table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Curso;

public class CursoTableModel extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;

	private List<Curso> valores;
	
	public CursoTableModel (List<Curso> valores){
		this.valores = valores;
	}
	
	@Override
	public int getRowCount() {
		return valores.size();
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Curso curso = valores.get(rowIndex);
		if (columnIndex == COL_ID){
			return curso.getId();
		}else if (columnIndex == COL_NOME){
			return curso.getNome();
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
			    coluna = "Nome do Curso";
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
		}
		return null;
	}
	
	public Curso get(int row){
		return valores.get(row);
	}

}

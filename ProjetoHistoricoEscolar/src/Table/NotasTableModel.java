package Table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Notas;

public class NotasTableModel extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_PRIMEIRA_UNIDADE = 1;
	private static final int COL_SEGUNDA_UNIDADE = 2;
	private static final int COL_NOTA_FINAL = 3;
	
	private List<Notas> valores;
	
	public NotasTableModel (List<Notas> valores){
		this.valores = valores;
	}
	
	@Override
	public int getRowCount() {
		return valores.size();
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Notas notas = valores.get(rowIndex);
		if (columnIndex == COL_ID){
			return notas.getId();
		}else if (columnIndex == COL_PRIMEIRA_UNIDADE){
			return notas.getPrimeiraUnidade();
		}else if (columnIndex == COL_SEGUNDA_UNIDADE){
			return notas.getSegundaUnidade();
		}else if (columnIndex == COL_NOTA_FINAL){
			return notas.getNotaFinal();
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
		    case COL_PRIMEIRA_UNIDADE:
			    coluna = "1° Unidade";
		        break;
	        case COL_SEGUNDA_UNIDADE:
		        coluna = "2° Unidade";
	            break;
	        case COL_NOTA_FINAL:
		        coluna = "Nota Final";
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
		}else if (columnIndex == COL_PRIMEIRA_UNIDADE){
			return String.class;
		}else if (columnIndex == COL_SEGUNDA_UNIDADE){
			return String.class;
		}else if (columnIndex == COL_NOTA_FINAL){
			return String.class;
		}
		return null;
	}
	
	public Notas get(int row){
		return valores.get(row);
	}

}

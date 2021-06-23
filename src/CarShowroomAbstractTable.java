

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CarShowroomAbstractTable extends AbstractTableModel {

    List<CarShowroom> data;
    String[] columns = {"Nazwa", "Pojemnosc"};

    public CarShowroomAbstractTable(List<CarShowroom> data) {
        this.data = data;
       // this.columns = columns;
    }

    public List<CarShowroom> getData()
    {
        return data;
    }

    public String[] getColumns()
    {
        return columns;
    }

    public void setData(List<CarShowroom> data) {
        this.data = data;
        this.fireTableDataChanged();
    }


    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return data.get(rowIndex).getNazwaSalonu();
            case 1:
                return data.get(rowIndex).getPojemnosc();
        }
        return null;
    }

    public void removeRow(int Index)
    {
        data.remove(Index);
        this.fireTableDataChanged();
    }

    public void addRow(CarShowroom salon)
    {
        data.add(salon);
        this.fireTableDataChanged();
    }
}

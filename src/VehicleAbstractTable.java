import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VehicleAbstractTable extends AbstractTableModel {

    private List<Vehicle> data = new ArrayList<>();
    String[] columns = {"Marka", "Model", "Stan", "Cena", "Rok", "Przebieg", "Silnik"};


    public VehicleAbstractTable(List<Vehicle> data)
    {
        this.data = data;

        //this.columns = columns;
    }

    @Override
    public String getColumnName(int column) { return columns[column];}


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public List<Vehicle> getData()
    {
        return data;
    }

    public void setData(List<Vehicle> data)
    {
        this.data = data;
        this.fireTableDataChanged();
    }

    public void addRow(Vehicle v)
    {
        this.data.add(v);
        this.fireTableDataChanged();
    }

    public void removeRow(int index)
    {
        this.data.remove(index);
        this.fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 0:
                return data.get(rowIndex).getMarka();
            case 1:
                return data.get(rowIndex).getModel();
            case 2:
                return data.get(rowIndex).getStan();
            case 3:
                return data.get(rowIndex).getCena();
            case 4:
                return data.get(rowIndex).getRokProdkucji();
            case 5:
                return data.get(rowIndex).getPrzebieg();
            case 6:
                return data.get(rowIndex).getPojemnoscSilnika();

        }
        return null;
    }
}

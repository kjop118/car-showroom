import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class App {

    VehicleAbstractTable vehicleModel;
    CarShowroomAbstractTable showroomModel;

    private JPanel panel;
    private JTable carShowroom;
    private JTable vehicles;
    private JLabel Marka;
    private JTextField markaText;
    private JLabel Nazwa;
    private JTextField nazwaSalon;
    private JTextField modelText;
    private JLabel Model;
    private JTextField pojemnoscSalon;
    private JLabel Pojemnosc;
    private JTextField rokText;
    private JTextField przebiegText;
    private JLabel Stan;
    private JComboBox stan;
    private JLabel Rok;
    private JLabel Przebieg;
    private JTextField silnikText;
    private JLabel Silnik;
    private JLabel Vehicle;
    private JLabel CarShowroom;
    private JButton addCarButton;
    private JButton getCarButton;
    private JButton removeCarButton;
    private JButton addCenterButton;
    private JButton removeCenterButton;
    private JTextField cenaText;
    private JLabel Cena;
    private JTextField infoText;
    private JButton sortName;
    private JButton sortAmount;
    private JButton sortVolume;
    private JButton fillingButton;
    private JLabel resultLabel;

    CarShowroom salon1 = new CarShowroom("Samochodzik", 15);
    CarShowroom salon2 = new CarShowroom("Brum brum", 20);
    CarShowroom salon3 = new CarShowroom("Pan Samochodzik", 5);
    CarShowroomContainer salonContainer = new CarShowroomContainer();


    Vehicle renault = new Vehicle("renault", "megan", ItemCondition.DAMAGED, 200000, 2018, 50000, 60);
    Vehicle fiat = new Vehicle("fiat", "panda", ItemCondition.NEW, 57000, 2020, 10, 50);
    Vehicle bmw = new Vehicle("bmw", "m3", ItemCondition.NEW, 200000, 2020, 20, 70);
    Vehicle bmw2 = new Vehicle("bmw", "x2", ItemCondition.USED, 120000, 2015, 20000, 55);
    Vehicle audi = new Vehicle("audi", "q8", ItemCondition.DAMAGED, 20000, 1999, 250000, 45);



    public App() {

        infoText.setEnabled(false);
        salonContainer.setShowroom(salon1);
        salonContainer.setShowroom(salon2);
        salonContainer.setShowroom(salon3);

        List<CarShowroom> showrooms = new ArrayList<>();
        showrooms = salonContainer.getList();


        salon1.addProduct(bmw);
        salon1.addProduct(bmw2);
        salon1.addProduct(audi);
        salon1.addProduct(bmw);
        salon1.addProduct(bmw);

        salon2.addProduct(fiat);
        salon2.addProduct(fiat);
        salon2.addProduct(audi);
        salon2.addProduct(audi);
        salon2.addProduct(renault);
        salon2.addProduct(renault);

        salon3.addProduct(fiat);
        salon3.addProduct(fiat);
        salon3.addProduct(fiat);
        salon3.addProduct(fiat);
        salon3.addProduct(fiat);


        createTableShowroom(showrooms);
        defaultVehicleTable();

        createItemConditionCombo();

        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = carShowroom.getSelectedRow();
                if(selectedRow >= 0 )
                {
                    String carMark = markaText.getText();
                    String carModel = modelText.getText();
                    String condition = (String) stan.getItemAt(stan.getSelectedIndex());

                    int price = 0;
                    int year = 0;
                    int km = 0;
                    int fuel = 0;
                    ItemCondition conditionEnum = ItemCondition.valueOf(condition);

                    if(!cenaText.getText().isEmpty()){ price = Integer.parseInt(cenaText.getText()); }
                    if(!rokText.getText().isEmpty()){ year = Integer.parseInt(rokText.getText()); }
                    if(!przebiegText.getText().isEmpty()){ km = Integer.parseInt(przebiegText.getText()); }
                    if(!silnikText.getText().isEmpty()){ fuel = Integer.parseInt(silnikText.getText()); }

                    if (markaText.getText() == null || markaText.getText().trim().isEmpty()) alertTextField();
                    else if(modelText.getText() == null || modelText.getText().trim().isEmpty()) alertTextField();
                    else if(cenaText.getText().trim().isEmpty()) alertTextField();
                    else if(rokText.getText().trim().isEmpty()) alertTextField();
                    else if(przebiegText.getText().trim().isEmpty()) alertTextField();
                    else if(silnikText.getText().trim().isEmpty()) alertTextField();
                    else{


                        String valueAt = (String)carShowroom.getValueAt(selectedRow, 0);
                        CarShowroom showroom = salonContainer.getSalony().get(valueAt);

                        int ilosc = showroom.getSamochody().size();
                        int volume = showroom.getPojemnosc();

                        showroom.addProduct(new Vehicle(carMark, carModel, conditionEnum, price, year, km, fuel));
                        List<Vehicle> vehicles = showroom.getSamochody();
                        createTableVehicles(vehicles);


                        markaText.setText("");
                        modelText.setText("");
                        cenaText.setText("");
                        rokText.setText("");
                        przebiegText.setText("");
                        silnikText.setText("");
                    }
                }
                else {
                    alertVehilce();
                }

            }

        });


        carShowroom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = carShowroom.getSelectedRow();
                String valueAt = (String)carShowroom.getValueAt(selectedRow, 0);
                CarShowroom showroom = salonContainer.getSalony().get(valueAt);
                List<Vehicle> vehicles = showroom.getSamochody();
                createTableVehicles(vehicles);

            }
        });


        addCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String showroomName = nazwaSalon.getText();
                int showroomVolume = 0;

                if(!pojemnoscSalon.getText().isEmpty()){ showroomVolume = Integer.parseInt(pojemnoscSalon.getText()); }

                if (nazwaSalon.getText() == null || nazwaSalon.getText().trim().isEmpty()) alertTextField();
                else if(pojemnoscSalon.getText().trim().isEmpty()) alertTextField();
                else {
                    salonContainer.addCenter(showroomName, showroomVolume);
                    List<CarShowroom> showrooms = new ArrayList<>();
                    showrooms = salonContainer.getList();
                    createTableShowroom(showrooms);
                    nazwaSalon.setText("");
                    pojemnoscSalon.setText("");
                }
            }
        });
        removeCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carShowroom.getSelectedRow();
                if(selectedRow >= 0)
                {
                    String valueAt = (String)carShowroom.getValueAt(selectedRow, 0);
                    salonContainer.removeCenter(valueAt);
                    List<CarShowroom> salony = new ArrayList<>();
                    salony = salonContainer.getList();
                    createTableShowroom(salony);
                    defaultVehicleTable();
                }
                else
                {
                    alertShowroom();
                }
            }
        });

        removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow1 = carShowroom.getSelectedRow();
                int selectedRow2 = vehicles.getSelectedRow();

                if(selectedRow1 >= 0)
                {
                    if(selectedRow2 >= 0) {

                        String valueAt = (String) carShowroom.getValueAt(selectedRow1, 0);
                        CarShowroom showroom = salonContainer.getSalony().get(valueAt);
                        Vehicle vehicle = showroom.getSamochody().get(selectedRow2);
                        showroom.removeProduct(vehicle);
                        List<Vehicle> vehicles = showroom.getSamochody();
                        createTableVehicles(vehicles);
                    }
                    else
                    {
                        alertVehilce();
                    }
                }
                else
                {
                    alertVehilce();
                }
            }
        });
        getCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow1 = carShowroom.getSelectedRow();
                int selectedRow2 = vehicles.getSelectedRow();

                if(selectedRow1 >= 0)
                {
                    if(selectedRow2 >= 0) {

                        String valueAt = (String) carShowroom.getValueAt(selectedRow1, 0);
                        CarShowroom showroom = salonContainer.getSalony().get(valueAt);
                        Vehicle vehicle = showroom.getSamochody().get(selectedRow2);
                        showroom.getProdukt(vehicle);
                        List<Vehicle> vehicles = showroom.getSamochody();
                        createTableVehicles(vehicles);

                        String nazwa = vehicle.getMarka() + " " + vehicle.getModel();
                        String ilosc = Integer.toString(vehicle.getIlosc());
                        int amount = vehicle.getIlosc();
                        if(amount == 0) {

                            infoText.setText("");
                        }
                        else
                            infoText.setText("Amount of "+nazwa+": "+ilosc);

                    }
                    else
                    {
                        alertVehilce();
                    }
                }
                else
                {
                    alertVehilce();
                }
            }
        });
        vehicles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow1 = carShowroom.getSelectedRow();
                int selectedRow2 = vehicles.getSelectedRow();

                String valueAt = (String) carShowroom.getValueAt(selectedRow1, 0);
                CarShowroom showroom = salonContainer.getSalony().get(valueAt);
                Vehicle vehicle = showroom.getSamochody().get(selectedRow2);
                String nazwa = vehicle.getMarka()+" "+vehicle.getModel();
                String ilosc = Integer.toString(vehicle.getIlosc());
                infoText.setText("Amount of "+nazwa+": "+ilosc);

            }
        });
        sortName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carShowroom.getSelectedRow();

                if(selectedRow >= 0) {
                    String valueAt = (String) carShowroom.getValueAt(selectedRow, 0);
                    CarShowroom showroom = salonContainer.getSalony().get(valueAt);
                    List<Vehicle> vehicles = showroom.sortByName();
                    createTableVehicles(vehicles);
                }
                else
                    alertShowroom();
            }
        });
        sortAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carShowroom.getSelectedRow();

                if(selectedRow >= 0) {
                    String valueAt = (String) carShowroom.getValueAt(selectedRow, 0);
                    CarShowroom showroom = salonContainer.getSalony().get(valueAt);
                    List<Vehicle> vehicles = showroom.sortByAmount();
                    createTableVehicles(vehicles);
                }
                else
                    alertShowroom();
            }
        });

        cenaText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = cenaText.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    cenaText.setEditable(true);
                } else {
                    cenaText.setEditable(false);
                }
            }
        });

        rokText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = rokText.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    rokText.setEditable(true);
                } else {
                    rokText.setEditable(false);
                }
            }
        });

        przebiegText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = przebiegText.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    przebiegText.setEditable(true);
                } else {
                    przebiegText.setEditable(false);
                }
            }
        });

        silnikText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = silnikText.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' ) {
                    silnikText.setEditable(true);
                } else {
                    silnikText.setEditable(false);
                }
            }
        });

        pojemnoscSalon.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = pojemnoscSalon.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' ) {
                    pojemnoscSalon.setEditable(true);
                } else {
                    pojemnoscSalon.setEditable(false);
                }
            }
        });

        sortVolume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CarShowroom> showrooms = new ArrayList<>();
                showrooms = salonContainer.sortVolume();
                createTableShowroom(showrooms);
            }
        });
        fillingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carShowroom.getSelectedRow();

                if(selectedRow >= 0) {
                    String valueAt = (String) carShowroom.getValueAt(selectedRow, 0);
                    CarShowroom showroom = salonContainer.getSalony().get(valueAt);
                    double zapelnienie = showroom.zapelnienie();
                    infoText.setText("Filling: "+zapelnienie+"%");

                }
                else
                    alertShowroom();

            }
        });
    }

    public void createTableShowroom(List<CarShowroom> salony) {
        carShowroom.setModel(new CarShowroomAbstractTable(salony));
    }

    public void createTableVehicles(List<Vehicle> vehicle) {
        vehicles.setModel(new VehicleAbstractTable(vehicle));
    }


    public void createItemConditionCombo() {
        stan.setModel(new DefaultComboBoxModel(new String[]{"NEW", "USED", "DAMAGED"}));
    }
    public JPanel getPanel()
    {
        return panel;
    }

    public void alertShowroom()
    {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f,"No CarShowroom selected","Alert", JOptionPane.WARNING_MESSAGE);
    }

    public void alertVehilce()
    {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f,"No Vehicle selected","Alert", JOptionPane.WARNING_MESSAGE);
    }

    public void alertTextField()
    {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f,"Textfield can not be empty","Alert", JOptionPane.WARNING_MESSAGE);
    }

    public void defaultShowroomTable() {
        vehicles.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null}
                },
                new String[]{"Nazwa", "Pojemnosc"}
        ));
    }


    public void defaultVehicleTable() {
        vehicles.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null}
                },
                new String[]{"Marka", "Model", "Stan", "Cena", "Rok", "Przebieg", "Silnik"}
        ));
    }
}

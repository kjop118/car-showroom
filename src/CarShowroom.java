import javax.swing.*;
import java.util.*;

public class CarShowroom implements Comparator<String > {

    String nazwaSalonu;
    int pojemnosc;
    int licznik = 0;

    List<Vehicle> samochody = new ArrayList<>();

    CarShowroom(String nazwaSalonu,int pojemnosc)
    {
        this.nazwaSalonu = nazwaSalonu;
        this.pojemnosc = pojemnosc;
    }

    String getNazwaSalonu(){
        return nazwaSalonu;
    }
    int getLicznik() { return licznik; }
    int getPojemnosc() { return pojemnosc; }

    double zapelnienie()
    {
        //System.out.println("**********************");
        return ((double)getLicznik()/(double)getPojemnosc())*100;
    }

    void addProduct(Vehicle car)
    {
        int obecnosc = 0;
        String nazwa1 = car.getNazwa();
        int n = samochody.size();
        int i = 0;
        if(licznik == pojemnosc)
        {
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,"Magazyn pełny", "Alert",JOptionPane.WARNING_MESSAGE);
            System.err.println("Magazyn pełny");
        }
        else{
            if(licznik == 0)
            {
                samochody.add(car);
                car.ilosc = 1;
                licznik++;
            }
            else{
                for (i = 0; i<n; i++)
                {
                    String nazwa2 = samochody.get(i).getNazwa();
                    obecnosc = nazwa1.compareTo(nazwa2);
                    if(obecnosc==0)
                        break;
                }

                if(obecnosc==0){
                    samochody.get(i).setIlosc(1);
                    //car.setIlosc(1);
                    licznik++;
                }
                else{
                    car.ilosc = 1;
                    samochody.add(car);
                    licznik++;
                }
            }
        }

    }

    List<Vehicle> getSamochody()
    {
        return samochody;
    }

    void getProdukt(Vehicle car)
    {

        int ilosc = car.ilosc;
        if(ilosc >= 2)
        {
            car.iloscDown();
            licznik--;
        }
        else if(ilosc==0)
        {
            System.err.println("Brak produktu");
        }
        else
        {
            samochody.remove(car);
            car.iloscDown();
            licznik--;
        }

    }

    void removeProduct(Vehicle car)
    {
        int ilosc = car.ilosc;
        licznik -= ilosc;
        car.ilosc = 0;
        samochody.remove(car);
    }



    Vehicle search(String nazwa)
    {
        int k = samochody.size();
        Vehicle wynik = new Vehicle("0","0",ItemCondition.NEW, 0,0,0,0);
        int check=0;
        for (int i = 0; i<k; i++)
        {
            String nazwa2=samochody.get(i).getNazwa();
            if(nazwa.equals(nazwa2))
            {
                wynik = samochody.get(i);
                check=1;
            }
        }
        if(check==0)
        {
            System.err.println("Produkt nie istnieje");
        }
        return wynik;
    }



    List<Vehicle> searchPartial(String nazwa)
    {
        int k = samochody.size();
        List<Vehicle> search = new ArrayList<>();
        for (int i = 0; i<k; i++)
        {
            String nazwa2=samochody.get(i).getNazwa();

            //dodaje do listy samochód
            if(nazwa2.contains(nazwa))
                search.add(samochody.get(i));

        }
        return search;
    }

    int countByCondition(ItemCondition n)
    {
        int k = samochody.size();
        int check=-100;
        int stan=0;
        int ilosc=0;


        for (int i = 0; i<k; i++)
        {

            check = compare(n.toString(), samochody.get(i).stan.toString());
            ilosc = samochody.get(i).ilosc;
            if(check==0)
                stan+=ilosc;
        }

        return stan;
    }

    void summary() {

        int k = samochody.size();
        for (int i = 0; i < k; i++) {
            System.out.println("\n");
            samochody.get(i).print();
            System.out.println("Ilosc: "+samochody.get(i).ilosc);
        }
    }

   /* void sortByName(){
        int k = samochody.size();
        Collections.sort(samochody);
        for (int i = 0; i < k; i++) {
            System.out.println(samochody.get(i).nazwa);
        }

    }*/

    List<Vehicle> sortByName(){
        int k = samochody.size();
        Collections.sort(samochody, new VehicleNazwaComperator());
        List<Vehicle> sorted = new LinkedList();

        for (int i = 0; i < k; i++) {
            sorted.add(samochody.get(i));
            //System.out.println(samochody.get(i).nazwa);
        }
        return sorted;

    }

    List<Vehicle> sortByAmount()
    {
        int k = samochody.size();
        Collections.sort(samochody, new VehicleAmountComperator());
        List<Vehicle> sorted = new LinkedList();

        for (int i = 0; i < k; i++) {
            sorted.add(samochody.get(i));
        }
        return sorted;
    }


    Vehicle max()
    {
        return Collections.max(samochody, new VehicleAmountComperator());
    }

    @Override
    public int compare(String o1, String o2) {

        int sprawdzam = o1.compareTo(o2);
        return sprawdzam;
    }
}

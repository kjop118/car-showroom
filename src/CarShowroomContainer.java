import java.util.*;

public class CarShowroomContainer {

    HashMap<String,CarShowroom> salony = new HashMap<>();

    void addCenter(String nazwa, double pojemnosc)
    {
        int ilosc = (int)pojemnosc;
        CarShowroom nowy = new CarShowroom(nazwa,ilosc);
        salony.put(nazwa,nowy);
    }

    public void setShowroom(CarShowroom obiekt)
    {
        salony.put(obiekt.getNazwaSalonu(),obiekt);
    }

    void removeCenter(String salon)
    {
        salony.remove(salon);
    }

    List<CarShowroom> sortVolume()
    {
        List<CarShowroom> showrooms = getList();
        int k = showrooms.size();
        Collections.sort(showrooms, new ShowroomVolumeComperator());
        List<CarShowroom> sorted = new LinkedList();

        for (int i = 0; i < k; i++) {
            sorted.add(showrooms.get(i));
        }
        return sorted;
    }

    List<CarShowroom> getList()
    {
        List<CarShowroom> showrooms = new LinkedList<>(salony.values());
        return showrooms;
    }

    public HashMap<String, CarShowroom> getSalony() {
        return salony;
    }

    List findEmpty()
    {
        List<CarShowroom> empty = new LinkedList<>();
        int k = salony.size();
        for (int i=0; i<k; i++) {

            if(salony.get(i).getLicznik()==0) {
                empty.add(salony.get(i));
            }
        }
        return empty;
    }



    void summary()
    {
        double zapelnienie;
        for (String nazwa : salony.keySet()) {
            zapelnienie = salony.get(nazwa).zapelnienie();
            System.out.println(nazwa+"\nzapelnienie: "+zapelnienie+"%");
        }
    }


}

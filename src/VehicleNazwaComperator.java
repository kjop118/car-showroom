import java.util.Comparator;

public class VehicleNazwaComperator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        int nazwa;
        nazwa = o1.getNazwa().compareTo(o2.getNazwa());
        return nazwa;
    }
}

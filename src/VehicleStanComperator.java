
import java.util.Comparator;

public class VehicleStanComperator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        int stan;
        stan = o1.getStan().compareTo(o2.getStan());
        return stan;
    }
}

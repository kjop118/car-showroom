

import java.util.Comparator;

public class VehicleAmountComperator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        int amount;
        amount = Integer.compare((-1)*o1.getIlosc(), (-1)*o2.getIlosc());
        return amount;
    }
}

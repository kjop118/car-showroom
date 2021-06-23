
import java.util.Comparator;

public class ShowroomVolumeComperator implements Comparator<CarShowroom> {
    @Override
    public int compare(CarShowroom o1, CarShowroom o2) {
        int amount;
        amount = Integer.compare((-1)*o1.getPojemnosc(), (-1)*o2.getPojemnosc());
        return amount;
    }
}

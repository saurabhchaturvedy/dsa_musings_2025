package PostKarat06June.CommodityPrices;

import java.util.TreeMap;

public class CommodityPriceTracker {


    TreeMap<Long, Integer> timestampToPrices = new TreeMap<>();
    TreeMap<Integer, Integer> priceFrequency = new TreeMap<>();


    public void incrementFrequency(int price) {
        priceFrequency.put(price, priceFrequency.getOrDefault(price, 0) + 1);
    }

    public void decrementFrequency(int price) {

        int freq = priceFrequency.get(price);

        if (freq == 1) {

            priceFrequency.remove(price);
        } else {

            priceFrequency.put(price, freq - 1);
        }
    }

    public Integer getMaxPrice() {

        return priceFrequency.isEmpty() ? -1 : priceFrequency.lastKey();
    }


    public void insertOrUpdate(long timestamp, Integer price) {

        if (timestampToPrices.containsKey(timestamp)) {

            Integer oldPrice = timestampToPrices.get(timestamp);
            decrementFrequency(oldPrice);
        }

        timestampToPrices.put(timestamp, price);
        incrementFrequency(price);

    }


    public static void main(String[] args) {


        CommodityPriceTracker tracker = new CommodityPriceTracker();

        // Sample data insertions and updates
        tracker.insertOrUpdate(1001, 50);
        tracker.insertOrUpdate(1002, 60);
        tracker.insertOrUpdate(1003, 55);
        tracker.insertOrUpdate(1001, 65); // Update existing timestamp
        tracker.insertOrUpdate(1004, 58);

        System.out.println("Max commodity price: " + tracker.getMaxPrice());  // Output: 65

        tracker.insertOrUpdate(1002, 45); // Update 60 -> 45
        System.out.println("Max commodity price after update: " + tracker.getMaxPrice());  // Output: 65

        tracker.insertOrUpdate(1001, 40); // Update 65 -> 40
        System.out.println("Max commodity price after update: " + tracker.getMaxPrice());
    }
}

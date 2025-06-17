package DSA.CommodityPrices.LevelUp1;

import java.util.TreeMap;

public class CommodityPriceTracker {


    TreeMap<Long, Integer> timestampToPrices = new TreeMap<>();
    TreeMap<Integer, Integer> priceFrequencyMap = new TreeMap<>();


    public void increaseFrequency(int price) {

        priceFrequencyMap.put(price, priceFrequencyMap.getOrDefault(price, 0) + 1);
    }


    public Integer getMaxCommodityPrice()
    {

        return priceFrequencyMap.isEmpty() ? -1 : priceFrequencyMap.lastKey();
    }

    public void decrementFrequency(int price) {

        Integer frequency = priceFrequencyMap.get(price);

        if (frequency == 1) {

            priceFrequencyMap.remove(price);
        } else {

            priceFrequencyMap.put(price, frequency - 1);
        }


    }


    public void insertOrUpdate(long timestamp, int price) {


        if (timestampToPrices.containsKey(timestamp)) {

            Integer oldPrice = timestampToPrices.get(timestamp);
            decrementFrequency(oldPrice);
        }


        timestampToPrices.put(timestamp, price);
        increaseFrequency(price);
    }


    public static void main(String[] args) {


        CommodityPriceTracker tracker = new CommodityPriceTracker();

        // Sample data insertions and updates
        tracker.insertOrUpdate(1001, 50);
        tracker.insertOrUpdate(1002, 60);
        tracker.insertOrUpdate(1003, 55);
        tracker.insertOrUpdate(1001, 65); // Update existing timestamp
        tracker.insertOrUpdate(1004, 58);

        System.out.println("Max commodity price: " + tracker.getMaxCommodityPrice());  // Output: 65

        tracker.insertOrUpdate(1002, 45); // Update 60 -> 45
        System.out.println("Max commodity price after update: " + tracker.getMaxCommodityPrice());  // Output: 65

        tracker.insertOrUpdate(1001, 40); // Update 65 -> 40
        System.out.println("Max commodity price after update: " + tracker.getMaxCommodityPrice());
    }
}

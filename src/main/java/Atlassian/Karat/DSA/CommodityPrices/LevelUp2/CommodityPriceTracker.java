package DSA.CommodityPrices.LevelUp2;

import java.util.TreeMap;

public class CommodityPriceTracker {


    TreeMap<Long, Integer> timestampToPrices = new TreeMap<>();
    TreeMap<Integer, Integer> priceFrequencyMap = new TreeMap<>();

    int  MAX_PRICE = Integer.MIN_VALUE;


    public void increaseFrequency(int price) {

        priceFrequencyMap.put(price, priceFrequencyMap.getOrDefault(price, 0) + 1);
    }


    public void decreaseFrequency(int price) {

        Integer frequency = priceFrequencyMap.get(price);

        if (frequency == 1) {

            priceFrequencyMap.remove(price);
        } else {

            priceFrequencyMap.put(price, frequency - 1);
        }
    }


    public void refreshMaxPrice() {

        MAX_PRICE = Integer.MIN_VALUE;

        for (Integer price : priceFrequencyMap.keySet()) {

            if (price > MAX_PRICE) {

                MAX_PRICE = price;
            }
        }
    }


    public void insertOrUpdate(long timestamp, Integer price) {


        if (timestampToPrices.containsKey(timestamp)) {

            Integer oldPrice = timestampToPrices.get(timestamp);
            decreaseFrequency(oldPrice);

            if (oldPrice == MAX_PRICE && !priceFrequencyMap.containsKey(MAX_PRICE)) {

                refreshMaxPrice();
            }
        }

        timestampToPrices.put(timestamp, price);
        increaseFrequency(price);

        if (price > MAX_PRICE) {

            MAX_PRICE = price;
        }

    }


    public Integer getMaxCommodityPrice() {


        return priceFrequencyMap.isEmpty() ? -1 : MAX_PRICE;
    }


    public static void main(String[] args) {


        CommodityPriceTracker tracker = new CommodityPriceTracker();

        tracker.insertOrUpdate(1001, 50);
        tracker.insertOrUpdate(1002, 60);
        tracker.insertOrUpdate(1003, 55);
        tracker.insertOrUpdate(1001, 65); // Overwrite timestamp 1001 from 50 → 65
        tracker.insertOrUpdate(1004, 58);

        System.out.println("Max commodity price: " + tracker.getMaxCommodityPrice());  // 65

        tracker.insertOrUpdate(1002, 45); // Update 60 → 45
        System.out.println("Max commodity price after update: " + tracker.getMaxCommodityPrice());  // 65

        tracker.insertOrUpdate(1001, 40); // Update 65 → 40 (will trigger recompute)
        System.out.println("Max commodity price after update: " + tracker.getMaxCommodityPrice());  // 58
    }
}

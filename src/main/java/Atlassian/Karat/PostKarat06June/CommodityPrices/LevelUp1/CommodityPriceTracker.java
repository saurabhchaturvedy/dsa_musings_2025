package PostKarat06June.CommodityPrices.LevelUp1;

import java.util.TreeMap;

public class CommodityPriceTracker {


    TreeMap<Long, Integer> timestampToPrices = new TreeMap<>();
    TreeMap<Integer, Integer> priceFrequency = new TreeMap<>();

    int maxPrice = Integer.MIN_VALUE;


    public Integer getMaxCommodityPrice() {

        return priceFrequency.isEmpty() ? -1 : maxPrice;
    }


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


    public void recomputeMaxPrice() {

        maxPrice = Integer.MIN_VALUE;

        for (Integer price : priceFrequency.keySet()) {

            if (price > maxPrice) {

                maxPrice = price;
            }
        }
    }


    public void insertOrUpdate(long timestamp,int price)
    {

        if(timestampToPrices.containsKey(timestamp))
        {

            int oldPrice = timestampToPrices.get(timestamp);
            decrementFrequency(oldPrice);

            if(oldPrice==maxPrice && !priceFrequency.containsKey(maxPrice))
            {
                recomputeMaxPrice();
            }
        }

        timestampToPrices.put(timestamp,price);
        incrementFrequency(price);

        if(price > maxPrice)
        {
            maxPrice = price;
        }
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

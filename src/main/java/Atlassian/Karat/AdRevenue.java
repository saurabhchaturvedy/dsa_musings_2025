

import java.util.*;

public class AdRevenue {


    public static List<String> calculateAdImpact(List<String> purchaseIds, List<String> adClicksData, List<String> userIps) {


        Map<String, String> ipAddresstoUserID = new HashMap<>();

        for (String s : userIps) {

            String[] userIpData = s.split(",");
            String userId = userIpData[0];
            String iPAddress = userIpData[1];

            ipAddresstoUserID.put(iPAddress, userId);
        }


        Set<String> puschaseSet = new HashSet<>(purchaseIds);
        Map<String, int[]> adTextToAdClickToPurchaeMap = new HashMap<>();

        for (String adClick : adClicksData) {

            String[] adClickLog = adClick.split(",");

            String ipAddress = adClickLog[0];
            String adText = adClickLog[2];


            adTextToAdClickToPurchaeMap.putIfAbsent(adText, new int[2]);

            adTextToAdClickToPurchaeMap.get(adText)[0]++;


            if (ipAddresstoUserID.containsKey(ipAddress) && puschaseSet.contains(ipAddresstoUserID.get(ipAddress))) {

                adTextToAdClickToPurchaeMap.get(adText)[1]++;
            }

        }


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, int[]> entry : adTextToAdClickToPurchaeMap.entrySet()) {

            String adText = entry.getKey();

            Integer clicks = entry.getValue()[0];
            Integer purchaseCount = entry.getValue()[1];


            result.add(purchaseCount + " of  " + clicks + " " + adText);

        }


        return result;
    }


    public static void main(String[] args) {


        String[] purchaseIds = {"3123122444", "234111110", "8321125440", "99911063"};

        String[] all_user_ips = {"2339985511,122.121.0.155", "234111110,122.121.0.1", "3123122444,92.130.6.145", "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000", "8321125440,82.1.106.8", "99911063,92.130.6.144"};


        String[] ad_clicks = {

                "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets", "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens", "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats", "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets", "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets", "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",};


        List<String> adstats = calculateAdImpact(Arrays.asList(purchaseIds), Arrays.asList(ad_clicks), Arrays.asList(all_user_ips));

        System.out.println(adstats);
    }
}
package KaratFriday;

import java.util.*;

public class DomainVisit3 {


    public static List<String> adBusinessImpactAnalysis(List<String> purchaseUserIds, List<String> adClicksData, List<String> userIPAddresses) {


        List<String> result = new ArrayList<>();

        Map<String, String> ipAddressToUserMap = new HashMap<>();


        for (String userIpAddress : userIPAddresses) {

            String[] ipAddressRecord = userIpAddress.split(",");
            String userId = ipAddressRecord[0];
            String ipAddress = ipAddressRecord[1];

            ipAddressToUserMap.put(ipAddress, userId);
        }


        Set<String> purchaseData = new HashSet<>(purchaseUserIds);
        Map<String, int[]> adTextToClickPurchaseMap = new HashMap<>();


        for (String adTextData : adClicksData) {

            String[] adRecord = adTextData.split(",");

            String ipAddress = adRecord[0];
            String addText = adRecord[2];

            adTextToClickPurchaseMap.putIfAbsent(addText, new int[2]);
            adTextToClickPurchaseMap.get(addText)[0]++;

            if (ipAddressToUserMap.containsKey(ipAddress) && purchaseData.contains(ipAddressToUserMap.get(ipAddress))) {

                adTextToClickPurchaseMap.get(addText)[1]++;
            }
        }


        for (Map.Entry<String, int[]> entry : adTextToClickPurchaseMap.entrySet()) {


            String adText = entry.getKey();

            int clicked = entry.getValue()[0];
            Integer purchased = entry.getValue()[1];

            result.add(purchased + " of " + clicked + " " + adText);
        }


        return result;
    }

    public static void main(String[] args) {


        String[] purchaseIds = {"3123122444", "234111110", "8321125440", "99911063"};

        String[] all_user_ips = {"2339985511,122.121.0.155", "234111110,122.121.0.1", "3123122444,92.130.6.145", "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000", "8321125440,82.1.106.8", "99911063,92.130.6.144"};


        String[] ad_clicks = {

                "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets", "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens", "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats", "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets", "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets", "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",};


        List<String> adstats = adBusinessImpactAnalysis(Arrays.asList(purchaseIds), Arrays.asList(ad_clicks), Arrays.asList(all_user_ips));

        System.out.println(adstats);
    }
}

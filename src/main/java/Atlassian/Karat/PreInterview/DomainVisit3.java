package PreInterview;

import java.util.*;

public class DomainVisit3 {


    public static List<String> adImpactAnalysis(List<String> purchaseIds, List<String> adClicksData, List<String> userIpAddress) {


        Map<String, String> ipAddressToUSer = new HashMap<>();


        for (String userip : userIpAddress) {

            String[] record = userip.split(",");
            String userId = record[0];
            String ipaddress = record[1];

            ipAddressToUSer.put(ipaddress, userId);
        }


        Map<String, int[]> adTextToClickPurchaseMap = new HashMap<>();
        Set<String> purchaseData = new HashSet<>(purchaseIds);


        for (String adData : adClicksData) {


            String[] adClickDataRecord = adData.split(",");

            String ipaddress = adClickDataRecord[0];
            String adText = adClickDataRecord[2];


            adTextToClickPurchaseMap.putIfAbsent(adText, new int[2]);
            adTextToClickPurchaseMap.get(adText)[0]++;

            if (ipAddressToUSer.containsKey(ipaddress) && purchaseData.contains(ipAddressToUSer.get(ipaddress))) {

                adTextToClickPurchaseMap.get(adText)[1]++;
            }
        }


        List<String> result = new ArrayList<>();

        for(Map.Entry<String,int[]> entry : adTextToClickPurchaseMap.entrySet())
        {


            String adText = entry.getKey();

            Integer clicked = entry.getValue()[0];
            Integer purchased = entry.getValue()[1];


            result.add(purchased+" of "+clicked+" "+adText);

        }


        return result;

    }

    public static void main(String[] args) {




        String[] purchaseIds = {"3123122444", "234111110", "8321125440", "99911063"};

        String[] all_user_ips = {"2339985511,122.121.0.155", "234111110,122.121.0.1", "3123122444,92.130.6.145", "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000", "8321125440,82.1.106.8", "99911063,92.130.6.144"};


        String[] ad_clicks = {

                "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets", "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens", "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats", "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets", "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets", "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",};


        List<String> adstats = adImpactAnalysis(Arrays.asList(purchaseIds), Arrays.asList(ad_clicks), Arrays.asList(all_user_ips));

        System.out.println(adstats);
    }
}

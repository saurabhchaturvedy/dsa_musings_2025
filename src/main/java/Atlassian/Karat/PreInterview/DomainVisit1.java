package PreInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainVisit1 {


    public static List<String> domainVisit1(String[] cpDomains) {

        Map<String, Integer> domainToVisitCountMap = new HashMap<>();


        for (String cpdomain : cpDomains) {

            String[] domainReccord = cpdomain.split(" ");
            Integer count = Integer.parseInt(domainReccord[0]);
            String domain = domainReccord[1];
            int length = domain.length();

            if (domainToVisitCountMap.containsKey(domain)) {


                domainToVisitCountMap.put(domain, domainToVisitCountMap.get(domain) + count);
            } else {

                domainToVisitCountMap.put(domain, count);
            }

            for (int i = 0; i < length; i++) {

                if (domain.charAt(i) == '.') {

                    String temp = domain.substring(i + 1, length);


                    if (domainToVisitCountMap.containsKey(temp)) {


                        domainToVisitCountMap.put(temp, domainToVisitCountMap.get(temp) + count);
                    } else {


                        domainToVisitCountMap.put(temp, count);
                    }

                }
            }
        }


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : domainToVisitCountMap.entrySet()) {


            String name = entry.getKey();
            Integer visitCount = entry.getValue();

            result.add(visitCount + " " + name);
        }


        return result;

    }


    public static void main(String[] args) {



        String[] str = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};


        List<String> result = domainVisit1(str);

        System.out.println(result);
    }
}

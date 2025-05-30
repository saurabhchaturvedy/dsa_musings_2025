package KaratFriday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainVisit1 {


    public List<String> domainVisit1(String[] cpDomains) {


        Map<String, Integer> domainVisitCountMap = new HashMap<>();

        for (String cpDomain : cpDomains) {

            String[] domainAttr = cpDomain.split(" ");
            Integer count = Integer.parseInt(domainAttr[0]);
            String domain = domainAttr[1];
            int length = domain.length();

            if (domainVisitCountMap.containsKey(domain)) {

                domainVisitCountMap.put(domain, domainVisitCountMap.get(domain) + count);
            } else {


                domainVisitCountMap.put(domain, count);
            }


            for (int i = 0; i < length; i++) {


                if (domain.charAt(i) == '.') {

                    String temp = domain.substring(i + 1, length);

                    if (domainVisitCountMap.containsKey(temp)) {

                        domainVisitCountMap.put(temp, domainVisitCountMap.get(temp) + count);
                    } else {

                        domainVisitCountMap.put(temp, count);
                    }
                }

            }
        }


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : domainVisitCountMap.entrySet()) {


            String name = entry.getKey();
            Integer count = entry.getValue();

            result.add(count + " " + count);
        }


        return result;

    }


    public static void main(String[] args) {

    }
}

package PostKarat06June.PopularContent;

import java.util.*;

public class PopularityConstant {


    Map<Integer, Integer> contentToPopularityMap;
    TreeMap<Integer, LinkedHashSet<Integer>> popularityToContentIdsMap;
    int mostRecentContentId;


    PopularityConstant() {

        this.contentToPopularityMap = new HashMap<>();
        this.popularityToContentIdsMap = new TreeMap<>();
        this.mostRecentContentId = -1;
    }


    public void increasePopularity(int contentId) {

        int currentPopularity = contentToPopularityMap.getOrDefault(contentId, 0);

        if (currentPopularity > 0) {

            popularityToContentIdsMap.get(currentPopularity).remove(contentId);
            if (popularityToContentIdsMap.get(currentPopularity).isEmpty()) {

                popularityToContentIdsMap.remove(currentPopularity);
            }
        }

        int newPopularity = currentPopularity + 1;
        contentToPopularityMap.put(contentId, newPopularity);
        popularityToContentIdsMap.computeIfAbsent(newPopularity, k -> new LinkedHashSet<>()).add(contentId);
        mostRecentContentId = contentId;
    }


    public void decreasePopularity(int contentId) {

        if (!contentToPopularityMap.containsKey(contentId)) {
            return;
        }


        int currentPopularity = contentToPopularityMap.getOrDefault(contentId, 0);

        popularityToContentIdsMap.get(currentPopularity).remove(contentId);
        if (popularityToContentIdsMap.get(currentPopularity).isEmpty()) {

            popularityToContentIdsMap.remove(currentPopularity);
        }

        int newPopularity = currentPopularity - 1;

        if (newPopularity > 0) {

            contentToPopularityMap.put(contentId, newPopularity);
            popularityToContentIdsMap.computeIfAbsent(newPopularity, k -> new LinkedHashSet<>()).add(contentId);
        } else {

            contentToPopularityMap.remove(contentId);
        }

        mostRecentContentId = contentId;
    }


    public int mostPopular() {

        if (popularityToContentIdsMap.isEmpty()) {
            return -1;
        }


        return popularityToContentIdsMap.lastEntry().getValue().getFirst();

    }


    public List<Integer> topKPopular(int topK) {

        List<Integer> result = new ArrayList<>();


        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : popularityToContentIdsMap.descendingMap().entrySet()) {

            LinkedHashSet<Integer> list = entry.getValue();

            for (Integer contentId : list) {

                result.add(contentId);
                if (result.size() == topK) {
                    break;
                }
            }


        }

        return result;
    }


    public Integer numberOfContentsWithAtleastPopularity(Integer K) {

        int count = 0;


        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : popularityToContentIdsMap.descendingMap().entrySet()) {

            int popularity = entry.getKey();

            if (popularity >= K) {

                LinkedHashSet<Integer> linkedHashSet = entry.getValue();
                count += linkedHashSet.size();
            } else {

                break;
            }


        }

        return count;
    }


    public static void main(String[] args) {



        PopularityConstant popularityCreator = new PopularityConstant();


        popularityCreator.increasePopularity(7);
        popularityCreator.increasePopularity(7);
        popularityCreator.increasePopularity(8);
        popularityCreator.increasePopularity(8);

        System.out.println(popularityCreator.mostPopular());

        popularityCreator.decreasePopularity(8);
        System.out.println(popularityCreator.mostPopular());

        popularityCreator.increasePopularity(9);
        popularityCreator.increasePopularity(9);
        popularityCreator.increasePopularity(9);
        popularityCreator.increasePopularity(9);
        popularityCreator.increasePopularity(10);
        popularityCreator.increasePopularity(10);

        System.out.println(popularityCreator.mostPopular());

        System.out.println(popularityCreator.topKPopular(2));

        System.out.println(popularityCreator.numberOfContentsWithAtleastPopularity(2));

    }
}
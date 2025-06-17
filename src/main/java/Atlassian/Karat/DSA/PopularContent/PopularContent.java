package DSA.PopularContent;

import java.util.*;

public class PopularContent {


    static class Content {

        Integer id;
        int popularity;
        long timestamp;

        public Content(Integer id, int popularity, long timestamp) {
            this.id = id;
            this.popularity = popularity;
            this.timestamp = timestamp;
        }


        public static Comparator<Content> descComparator() {

            return (a, b) -> {


                if (a.popularity != b.popularity) {

                    return Integer.compare(b.popularity, a.popularity);
                }

                return Long.compare(b.timestamp, a.timestamp);
            };
        }


        public static Comparator<Content> minHeapComparator() {

            return (a, b) -> {


                if (a.popularity != b.popularity) {

                    return Integer.compare(a.popularity, b.popularity);
                }

                return Long.compare(a.timestamp, b.timestamp);
            };
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Content content = (Content) o;
            return id == content.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }


    Map<Integer, Content> contentMap;
    TreeSet<Content> sortedContents;
    PriorityQueue<Content> kCachedContent;
    int kCached;
    long timestamp;

    public PopularContent() {
        this.contentMap = new HashMap<>();
        this.sortedContents = new TreeSet<>(Content.descComparator());
        this.kCachedContent = new PriorityQueue<>();
        this.kCached = -1;
        this.timestamp = 0L;
    }


    public void invalidateTopKCache() {

        kCached = -1;
        kCachedContent = null;
    }


    public void increasePopularity(Integer contentId) {


        timestamp++;


        Content c = contentMap.getOrDefault(contentId, new Content(contentId, 0, timestamp));

        sortedContents.remove(c);

        c.popularity++;
        c.timestamp = timestamp;

        sortedContents.add(c);

        contentMap.put(contentId, c);

        invalidateTopKCache();
    }


    public void decreasePopularity(Integer contentId) {

        if (!contentMap.containsKey(contentId)) return;


        timestamp++;

        Content c = contentMap.get(contentId);

        sortedContents.remove(c);

        c.popularity--;
        c.timestamp = timestamp;

        if (c.popularity > 0) {

            sortedContents.add(c);
            contentMap.put(contentId, c);
        } else {

            contentMap.remove(c);
        }


        invalidateTopKCache();
    }


    public Integer popularContent() {

        return sortedContents.isEmpty() ? -1 : sortedContents.first().id;
    }


    public List<Integer> topKPopularContent(int k) {


        if (kCachedContent != null && k == kCached) {


            List<Integer> result = new ArrayList<>();

            for (Content c : kCachedContent) {
                result.add(c.id);
            }

            return result;
        }

        PriorityQueue<Content> minHeap = new PriorityQueue<>(k, Content.minHeapComparator());

        for (Content c : sortedContents) {


            if (minHeap.size() < k) {

                minHeap.add(c);
            } else if (Content.descComparator().compare(c, minHeap.peek()) < 0) {

                break;
            } else {

                minHeap.poll();
                minHeap.add(c);
            }
        }


        List<Integer> result = new ArrayList<>();
        List<Content> ordered = new ArrayList<>(minHeap);
        ordered.sort(Content.descComparator());

        for (Content c : ordered) {

            result.add(c.id);
        }


        kCachedContent = new PriorityQueue<>(minHeap);
        kCached = k;

        return result;
    }


    public static void main(String[] args) {


        PopularContent popularityTracker = new PopularContent();


        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);

        System.out.println(popularityTracker.popularContent());


        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(7);

        System.out.println(popularityTracker.popularContent());
        System.out.println(popularityTracker.topKPopularContent(2));

        popularityTracker.increasePopularity(8);
        System.out.println(popularityTracker.topKPopularContent(3));
        System.out.println(popularityTracker.topKwithAtleastPopularity(3));

    }

    private Integer topKwithAtleastPopularity(int k) {


        int count = 0;

        for (Content c : sortedContents) {

            if (c.popularity < k) break;
            count++;
        }

        return count;
    }



}

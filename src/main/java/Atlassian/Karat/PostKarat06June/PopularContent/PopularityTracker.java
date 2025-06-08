package PostKarat06June.PopularContent;

import java.util.*;

public class PopularityTracker {


    static class Content {

        int id;
        int popularity;
        long lastUpdated;

        public Content(int id, int popularity, long lastUpdated) {
            this.id = id;
            this.popularity = popularity;
            this.lastUpdated = lastUpdated;
        }


        public static Comparator<Content> descComparator() {

            return (a, b) -> {

                if (a.popularity != b.popularity) {

                    return b.popularity - a.popularity;
                }

                return Long.compare(b.lastUpdated, a.lastUpdated);
            };
        }


        public static Comparator<Content> minHeapComparator() {

            return (a, b) -> {

                if (a.popularity != b.popularity) {

                    return a.popularity - b.popularity;
                }

                return Long.compare(a.lastUpdated, b.lastUpdated);
            };
        }


        public boolean equals(Object o) {

            return (o instanceof Content && ((Content) o).id == id);
        }

        public int hashCode() {

            return Objects.hash(id);
        }
    }


    Map<Integer, Content> contentMap;
    TreeSet<Content> sortedContents;
    PriorityQueue<Content> topKCached;
    int kCached;
    long timestamp;


    PopularityTracker() {


        this.contentMap = new HashMap<>();
        this.sortedContents = new TreeSet<>(Content.descComparator());
        this.topKCached = null;
        this.kCached = -1;
        this.timestamp = 0;
    }

    public void invalidateTopKCache() {

        this.topKCached = null;
        this.kCached = -1;
    }


    // O(log n)
    public void increasePopularity(int contentId) {


        timestamp++;


        Content c = contentMap.getOrDefault(contentId, new Content(contentId, 0, timestamp));

        sortedContents.remove(c);

        c.popularity++;
        c.lastUpdated = timestamp;

        sortedContents.add(c);
        contentMap.put(contentId, c);


        invalidateTopKCache();

    }


    // (O(log n))
    public void decreasePopularity(int contentId) {

        if (!contentMap.containsKey(contentId)) return;

        timestamp++;


        Content c = contentMap.get(contentId);

        sortedContents.remove(c);
        c.popularity--;
        c.lastUpdated = timestamp;

        if (c.popularity > 0) {

            sortedContents.add(c);
            contentMap.put(contentId, c);
        } else {

            contentMap.remove(contentId);
        }

        invalidateTopKCache();
    }


    // O(1)
    public int getMostPopular() {

        return sortedContents.isEmpty() ? -1 : sortedContents.first().id;
    }


    // O(klogk)
    public List<Integer> topKPopular(int k) {

        if (topKCached != null && k == kCached) {

            List<Integer> result = new ArrayList<>();
            for (Content c : topKCached) {
                result.add(c.id);
            }

            return result;
        }


        PriorityQueue<Content> heap = new PriorityQueue<>(k, Content.minHeapComparator());

        for (Content c : sortedContents) {

            if (heap.size() < k) {

                heap.offer(c);
            } else if (Content.descComparator().compare(c, heap.peek()) < 0) {
                break;
            } else {

                heap.poll();
                heap.offer(c);
            }

        }


        List<Integer> result = new ArrayList<>();
        List<Content> ordered = new ArrayList<>(heap);
        ordered.sort(Content.descComparator());

        for (Content c : ordered) {

            result.add(c.id);
        }


        topKCached = new PriorityQueue<>(heap);
        kCached = k;


        return result;
    }


    // O(m) m<=k
    public Integer topKwithAtleastPopularity(int k) {

        int count = 0;

        for (Content c : sortedContents) {

            if (c.popularity < k) break;
            count++;
        }

        return count;
    }


    public static void main(String[] args) {


        PopularityTracker popularityTracker = new PopularityTracker();


        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);

        System.out.println(popularityTracker.getMostPopular());


        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(7);

        System.out.println(popularityTracker.getMostPopular());
        System.out.println(popularityTracker.topKPopular(2));

        popularityTracker.increasePopularity(8);
        System.out.println(popularityTracker.topKPopular(3));
        System.out.println(popularityTracker.topKwithAtleastPopularity(3));


    }
}

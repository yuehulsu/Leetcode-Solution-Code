package instacart;

import java.util.*;

// 981. Time Based Key-Value Store
// https://leetcode.com/problems/time-based-key-value-store/solution/
// https://mkyong.com/java/how-to-get-current-timestamps-in-java/

public class LC981_TimeBasedKeyValueStore {
    static class KVStore {
        // Can you see me here?
        // I will start coding
        Map<String, TreeMap<Integer, String>> map; // Value: String -> TreeMap

        public KVStore() {
            map = new HashMap<>();
        }

        int set(String key, String value) {
            int timeStamp = (int)System.currentTimeMillis();

            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }

            map.get(key).put(timeStamp, value);

            return timeStamp;
        }

        String get(String key, int timeStamp) {
            // System.out.println(timeStamp);
            if (!map.containsKey(key)) return "";

            // If exist, then we try to get the tree map first
            TreeMap<Integer, String> treeMap = map.get(key);
            Integer index = treeMap.floorKey(timeStamp);

            return index != null ? treeMap.get(index) : "";
        }

        String get(String key) {
            int timeStamp = (int)System.currentTimeMillis();
            return get(key, timeStamp);
        }
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        KVStore kvStore = new KVStore();
        int timeStamp = kvStore.set("foo", "bar");

        // TimeUnit.SECONDS.sleep(1);
        Thread.sleep(1000);

        kvStore.set("foo", "bar2");

        String sol1 = kvStore.get("foo", timeStamp);
        System.out.println("Sol1 is : " + sol1); // "bar"

        String sol2 = kvStore.get("foo");
        System.out.println("Sol2 is : " + sol2); // "bar2"

        String sol3 = kvStore.get("foo", timeStamp + 750);
        System.out.println("Sol3 is : " + sol3); // "bar2"
    }
}
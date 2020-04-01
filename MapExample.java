import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {
    public static Map<String, Integer> generateMap(){
        List<String> list = Arrays.asList( "one", "two", "three", "four");
        List<Integer> integers = Arrays.asList( 1, 2, 3, 4);

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); ++i){
            map.put(list.get(i), integers.get(i));
        }
        System.out.println(map);
        return map;
    }

    public static TreeMap<String, Integer> generateTreeMap(){
        List<String> list = Arrays.asList( "ten", "hundred", "thousand", "zero");
        List<Integer> integers = Arrays.asList( 10, 100, 1_000, 0);

        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < list.size(); ++i){
            map.put(list.get(i), integers.get(i));
        }
        System.out.println(map);
        return map;
    }

    public static void duplicateAndClear(Map<String, Integer> map) {
        // We can look at clear()
        HashMap<String, Integer> duplicate = new HashMap<>(map);
        duplicate.clear();

        // Show isEmpty
        if(duplicate.isEmpty()){
            System.out.println("Duplicate is now officialy empty");
        }
        else {
            System.out.println("I must have missed something");
        }
    }

    public static void hasKey(Map<String, Integer> map, String key) {
        // look at containsKey
        if( map.containsKey(key)){
            System.out.println("We have a key " + key);
        }
        else{
            System.out.println("We don't have a key " + key);
        }
    }
    public static void hasValue(Map<String, Integer> map, Integer value) {
        // look at containsKey
        if( map.containsValue(value)){
            System.out.println("We have a value " + value);
        }
        else{
            System.out.println("We don't have a value " + value);
        }
    }

    public static void main(String[] args) {


        Map<String, Integer> map = generateMap(); 
        duplicateAndClear(map);

        String key = "one";
        hasKey(map, key);

        String nokey = "ten";
        hasKey(map, nokey);


        // make entries into a map
        map.put("ten", 23);
        map.put("one", 2);

        Integer val = 23;
        hasValue(map, val);

        Collection<Integer> values = map.values();
        System.out.println(values);

        // copy a collection to an ArrayList
        ArrayList<Integer> a = new ArrayList<>(values);

        // get an iterator
        Iterator<Integer> iter = a.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }


        // Let us get this TreeMap
        TreeMap<String,Integer> tree = generateTreeMap();

        tree.putAll(map);

        map.putAll(tree);
        map.add





        
    }

}
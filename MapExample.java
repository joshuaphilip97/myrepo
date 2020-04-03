import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public static Map<String, Integer> modifyMap() {
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

        return map;
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

    // This is a helper method to help with a correct format of a string 
    // that we can use to parse a given date-string
    public static LocalDate createDate(String dateStr){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        LocalDate date = LocalDate.parse(dateStr, dtf);
        return date;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = modifyMap();
        System.out.println(map);

        // First way to extract elements from the map
        // THIS IS WHAT I HOPE !!!!! you know
        // List<String> list
        // for( String str: list)
        // 
        // in a similar fashion, we recognize the type of our elements


        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for(Map.Entry<String, Integer>  pair :set) {
            String myKey = pair.getKey();
            Integer myValue = pair.getValue();
            System.out.println("key: " + myKey + " value " + myValue);
        }

        System.out.println("=========");

        for(Map.Entry<String, Integer>  pair :map.entrySet()){
            String myKey = pair.getKey();
            Integer myValue = pair.getValue();
            System.out.println("key: " + myKey + " value " + myValue);
        }

        // one more way, this time we are goint to use a set iterator
        Iterator<Map.Entry<String, Integer>> siter = set.iterator();
        while( siter.hasNext()) {
            Map.Entry<String, Integer> pair = siter.next();
            String str = pair.getKey();
            Integer value = pair.getValue();
            // modify the value for the key
            value = value + 100;
            map.put(str, value);
        }

        map.forEach((k, v) -> System.out.println("{k}: " + k + " {v}: " + v));

        String fileName = "test1.txt";
        // this is an old way to do it
        /*
        PrintWriter output = null;
        try {
            output = new PrintWriter(fileName);
            for (Map.Entry<String, Integer> pair : map.entrySet()) {
                String myKey = pair.getKey();
                Integer myValue = pair.getValue();
                output.write("key: " + myKey + " value " + myValue);

            }
        }
        catch(IOException e){
            System.out.println("Problems opening a file " + fileName);
        }
        finally{
            output.close();
        }
        */

        try (PrintWriter output = new PrintWriter(fileName);) {
            for (Map.Entry<String, Integer> pair : map.entrySet()) {
                String myKey = pair.getKey();
                Integer myValue = pair.getValue();
                output.println("key: " + myKey + " value " + myValue);
            }
        } catch (Exception e) {
            System.out.println("Trouble dealing with " + fileName);
        }

        // We are going to create a Map  to store info about the episodes of 
        // "The Last Ship"
        // We will use LocalDate as a key, 
        //             String with a Title as a value
        // Then, we are going to write it to a file.

        // This is just a quick test to check the current date
        LocalDate date = LocalDate.now();
        System.out.println(date);

        TreeMap<LocalDate, String> lastShip = new TreeMap<>();
        lastShip.put(LocalDate.of(2014, 6, 22), "Phase Six");

        LocalDate dx = createDate("Jun 02, 2014") ;
        lastShip.put(dx, "Welcome to Gitmo");

        // be mindfull of the correct format that is needed by the
        // the date formatter
        dx = createDate("Jul 06, 2014");
        lastShip.put(dx, "Dead Reckoning");

        dx = createDate("Jul 13, 2014");
        lastShip.put(dx, "We'll Get There");
        String fileName2 = "aha.txt";

        // Again, we are using try-catch-with
        try ( final PrintWriter output = new PrintWriter(fileName2);)
        {
            lastShip.forEach((k, v) -> output.println(k + " ~~~|_____/~~~ " + v));
        } catch (final IOException e) {
            System.out.println("Problems writing to " + fileName);
        }


    }

}
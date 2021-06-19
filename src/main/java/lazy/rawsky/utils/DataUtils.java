package lazy.rawsky.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class DataUtils {

    public static <K, V> Map<K, V> mapFromArrays(K[] keys, V[] values) {
        Map<K, V> map = new HashMap<>();
        if (keys.length != values.length)
            throw new IllegalStateException("The number of keys and values needs to be the same! You can't have entries with a single value or key.");
        for (int i = 0; i < keys.length; i++) {
            K key = keys[i];
            V value = values[i];
            if (map.containsKey(key))
                System.err.println("Key " + key + " is already specified with value " + map.get(key) + ".");
            else map.put(key, value);
        }
        return map;
    }

    public static <O> O[] equalArray(Class<O> clazz, int capacity, O value) {
        O[] array = (O[]) Array.newInstance(clazz, capacity);
        Arrays.fill(array, value);
        return array;
    }

    public static Integer[] numberedArray(int finish) {
        Integer[] arr = new Integer[finish];
        for (int i = 0; i < finish; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static Integer[] numberedArray(int start, int cap) {
        Integer[] arr = new Integer[cap];
        for (int i = 0; i < cap; i++) {
            arr[i] = start;
            start++;
        }
        return arr;
    }

    public static String[] enumStringArr(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}

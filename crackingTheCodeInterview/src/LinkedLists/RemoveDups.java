package LinkedLists;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class RemoveDups {

    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<>();

        strings.add("test");
        strings.add("test2");
        strings.add("test3");
        strings.add("test4");
        strings.add("test5");
        strings.add("test6");
        strings.add("test");
        strings.add("test2");

        for (String string : removeDuplicates(strings)) {
            System.out.println(string);
        }

    }

    private static LinkedList<String> removeDuplicates(LinkedList<String> linkedList) {
        Set<String> buffer = new HashSet<>();
        LinkedList<String> result = new LinkedList<>();
        for (String s : linkedList) {
            if(!buffer.contains(s)) {
                result.add(s);
            }
            buffer.add(s);
        }
        return result;
    }

}

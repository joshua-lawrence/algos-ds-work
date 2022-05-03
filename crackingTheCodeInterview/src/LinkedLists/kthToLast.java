package LinkedLists;

import java.util.LinkedList;

public class kthToLast {
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

        System.out.println(kthToLast(strings, 0));
    }

    private static <E> E kthToLast(LinkedList<E> linkedList, int k) {
        int size = linkedList.size();
        if(size - k - 1 > 0 && k > -1) {
            return linkedList.get(size - k - 1);
        }
        else {
            throw new IllegalArgumentException("Must be within the range of the list.");
        }
    }
}

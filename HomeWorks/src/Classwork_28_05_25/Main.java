package Classwork_28_05_25;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String a = "aplle";
        Map<Character, Long> map = a.chars().mapToObj(b -> (char)b).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
    }
}

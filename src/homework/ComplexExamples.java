package homework;

import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {
    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        System.out.println();
        System.out.println(ANSI_YELLOW + "Task_1" + ANSI_RESET);
        System.out.println();

        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println(ANSI_YELLOW + "Duplicate filtered, grouped by name, sorted by name and id:" + ANSI_RESET);
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key:Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */

        List<Person> personAsList = Arrays.asList(RAW_DATA);

        Map<String, Long> mapOfPerson = personAsList.stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        for (Map.Entry<String, Long> entryMapOfPersons : mapOfPerson.entrySet()) {
            System.out.println(ANSI_GREEN + "Key: " + ANSI_RESET + entryMapOfPersons.getKey() + ANSI_GREEN + "\nValue: " + ANSI_RESET + entryMapOfPersons.getValue());
        }


        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару именно в скобках, которые дают сумму - 10
         */

        System.out.println();
        System.out.println(ANSI_YELLOW + "Task_2 \n[3, 4, 2, 7], 10 -> [3, 7] - вывести пару именно в скобках, которые дают сумму - 10" + ANSI_RESET);
        System.out.println();

        List<Integer> listOftarget = Arrays.asList(twoSum(new int[]{3, 4, 2, 7}, 10));
        System.out.println(ANSI_GREEN + "Output: " + ANSI_RESET + listOftarget);

        /*
        Task3
            Реализовать функцию нечеткого поиска
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */

        System.out.println();
        System.out.println(ANSI_YELLOW + "Task_3 \nРеализовать функцию нечеткого поиска" + ANSI_RESET);
        System.out.println();

        System.out.println(printFuzzySearch("car", "ca6$$#_rtwheel"));
        System.out.println(printFuzzySearch("cwhl", "cartwheel"));
        System.out.println(printFuzzySearch("cwhee", "cartwheel"));
        System.out.println(printFuzzySearch("cartwheel", "cartwheel"));
        System.out.println(printFuzzySearch("cwheeel", "cartwheel"));
        System.out.println(printFuzzySearch("lw", "cartwheel"));

        System.out.println();
        System.out.println(ANSI_YELLOW + "Tests of Task_3" + ANSI_RESET);
        System.out.println();

        System.out.println(printFuzzySearch(null, "cartwheel"));
        System.out.println(printFuzzySearch("cartwheel", null));
        System.out.println(printFuzzySearch("Car", "cars"));

    }

    public static Integer[] twoSum(int[] nums, int targetSum){

        Integer[] targetArrey = new Integer[2];
        Map<Integer, Integer> complements = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            Integer complement = complements.get(nums[i]);
                if (complement != null){
                    targetArrey[0] = nums[complement];
                    targetArrey[1] = nums[i];
                 }
            complements.put(targetSum - nums[i], i);
            }
        return targetArrey;
    }

    public static boolean fuzzySearch(String quest, String request){

        if (quest == null || request == null){
            return false;
            }
        else if (quest.equalsIgnoreCase(request)){
            return true;
            }
        else {
            String[] mQuest = quest.split("");
            String[] mRequest = request.split("");

            int indexOfCompare = 0;

            for (String s : mRequest) {
                if (s.equalsIgnoreCase(mQuest[indexOfCompare])){
                    indexOfCompare++;
                }
                if (indexOfCompare == mQuest.length){
                return true;
                }
            }
        }
        return false;
    }

    public static String printFuzzySearch (String a, String b){
        return ANSI_GREEN + "Quest: " + ANSI_RESET + a + ANSI_GREEN + "\nRequest: " + ANSI_RESET + b + ANSI_GREEN + "\nCompare: " + ANSI_RESET + fuzzySearch(a, b) + "\n";
    }
}

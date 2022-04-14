import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("1. Количество несовершеннолетних: " + count);

        List<String> recruits = persons.stream()
                .filter(f -> f.getAge() >= 18 && f.getAge() < 27 && f.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("2. Фамилии призывников: ");
//        recruits.forEach(System.out::println);

        List<String> jobs = persons.stream()
                .filter((f) -> (f.getAge() >= 18 && f.getEducation() == Education.HIGHER))
                .filter((f) -> (f.getSex() == Sex.WOMAN && f.getAge() < 55) || (f.getSex() == Sex.MAN && f.getAge() < 60))
//                .map()
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
//        System.out.println("3. Фамилии работящих: ");
                jobs.forEach(System.out::println);
    }
}
//
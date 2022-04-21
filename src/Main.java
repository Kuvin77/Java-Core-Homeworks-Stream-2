import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // несовершеннолетние
        System.out.println(persons.stream()
                .filter(d -> d.getAge() < 25)
                .count());

        // призывники (MAN с 18 до 27)
        persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 17 & x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // работающие с высшим образованием
        persons.stream()
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> (x.getAge() < 60 & x.getSex() == Sex.WOMAN) ||
                        (x.getAge() < 65 & x.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .forEach(System.out::println);
    }
}

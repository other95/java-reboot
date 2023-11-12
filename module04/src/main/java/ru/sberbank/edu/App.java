package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Проверка класса Пользовательский цифровой сравниватель!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int max = 100;
        List<Integer> list = new ArrayList<>();
        for (int i = 1;i<=25;i++) {
            list.add( (int) (Math.random() * ++max));
        }
        list.set(5,null);
        System.out.println("Исходный массив 1 : " +list);
        Comparator<Integer> custDigitComp = new CustomDigitComparator();
        list.sort(custDigitComp);
        System.out.println("Отсортированный массив 1 : " +list);

        List<Person> list2 = new ArrayList<>();
        list2.add(new Person( "Иван", "Москва", 35));
        list2.add(new Person( "Иван", "Москва", 32));
        list2.add(new Person( "Карл", "Лондон", 40));
        list2.add(new Person( "Иван", "Питер", 12));
        list2.add(new Person( "Джанибек", "Казань", 36));

        System.out.println("Исходный массив 2 : " +list2);
        list2.sort(Person::compareTo);
        System.out.println("Отсортированный массив 2 : " +list2);
    }
}

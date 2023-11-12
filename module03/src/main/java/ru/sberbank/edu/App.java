package ru.sberbank.edu;

import java.sql.Array;

/**
 * Программа проверки работы класса CustomArray
 * Модуль 03 задача 01
 * Чильдинов С.М.
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomArrayImpl<String> listString = new CustomArrayImpl<String>();
        listString.add("Привет");
        listString.add("Мир");
        listString.add("!");
        listString.add("Еще строка");
        System.out.println( "CustomArray заполнен 4-мя строками : " + listString );

        String[] list2 = new String[] {"Winter", "Spring", "Summer", "Autumn"};
        // listString.addAll(list2);
        listString.addAll(2,list2);

        System.out.println( "в CustomArray со 2-го элемента добавлено еще 4-е строками : "+ listString );
        //System.out.println( listString.get(4) );
        //System.out.println( listString.get(12) );
        listString.set(4,"Лето");
        System.out.println( "в CustomArray изменен еще 4-й элемент : "+listString.get(4) );
        listString.remove(2);
        System.out.println( "из CustomArray удален еще 2-й элемент : "+listString );
        listString.remove("Spring");
        System.out.println( "из CustomArray удален элемент Spring: "+listString );
        System.out.println( "CustomArray содержит Лето " + listString.contains("Лето") );
        System.out.println( "CustomArray содержит Зима " + listString.contains("Зима") );
        System.out.println( "в CustomArray позиция Лето " +listString.indexOf("Лето") );
        System.out.println( "в CustomArray позиция Зима " + listString.indexOf("Зима") );
        System.out.println( "вместимость CustomArray "+listString.getCapacity() );
        listString.ensureCapacity(12);
        System.out.println( "увеличили вместимость CustomArray до "+ listString.getCapacity() );
        System.out.println( "CustomArray : "+listString );
        listString.reverse();
        System.out.println( "развернули CustomArray : "+listString );
        Object[] arr = listString.toArray();
        System.out.println("Поместили CustomArray в массив и выводим:");
        for (Object item: arr) {
            System.out.println((String)item);
        }

    }
}

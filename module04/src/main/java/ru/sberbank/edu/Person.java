package ru.sberbank.edu;

import java.util.Objects;

/***
 * Класс Person сравниваемый
 */
public class Person implements  Comparable<Person> {
    private String name;
    private String city;
    private Integer age;

    public Person (String name, String city, Integer age) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
    @Override
    public String toString() {
        return "[ Имя: "+name+", Город: "+city+", возраст: "+age+" ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        Person other = (Person) obj;
        return this.name.toUpperCase().equals(other.name.toUpperCase()) &&
                this.city.toUpperCase().equals(other.city.toUpperCase()) &&
                this.age.equals(other.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name.toUpperCase(), age, city.toUpperCase() );
    }

    public int compareTo(Person other) {
        int result = city.compareToIgnoreCase(other.city);
        if (result == 0) {
            result = name.compareToIgnoreCase(other.name);
        }
        return result;
    }
}

package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testEquals() {
        Person person1 = new Person( "Иван", "Москва", 35);
        Person person2 = new Person( "ИвАн", "МоСква", 35);
        Person person3 = new Person( "Иван", "Питер", 12);
        assertTrue(person1.equals(person2));
        assertFalse(person1.equals(person3));

    }
    @Test
    void compareTo() {
        Person person1 = new Person( "Иван", "Москва", 35);
        Person person2 = new Person( "ИвАн", "МоСква", 35);
        Person person3 = new Person( "Иван", "Питер", 12);
        assertEquals(person1.compareTo(person2),0);
        assertEquals(person1.compareTo(person3),-3);
    }

    @Test
    void testHashCode() {
        Person person1 = new Person( "Иван", "Москва", 35);
        Person person2 = new Person( "ИвАн", "МоСква", 35);
        Person person3 = new Person( "Иван", "Питер", 12);
        assertEquals(person1.hashCode(),person2.hashCode());
        assertNotEquals(person1.hashCode(),person3.hashCode());
    }
}
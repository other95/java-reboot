package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayImplTest {

    CustomArrayImpl<String> listString;
    private void initTestList() {
        listString = new CustomArrayImpl<>();
        listString.add("строка 1");
        listString.add("строка 2");
        listString.add("строка 3");
    }
    @Test
    void size() {
        initTestList();
        Assertions.assertEquals(listString.size(),3);
    }

    @Test
    void isEmpty() {
        initTestList();
        assertFalse(listString.isEmpty());
    }

    @Test
    void add() {
      initTestList();
      listString.add("строка 4");
        assertEquals("строка 4", listString.get(3));
    }

    @Test
    void addAllArray() {
        initTestList();
        String[] list2 = new String[] {"строка 4", "строка 5"};
        listString.addAll(list2);
        Assertions.assertEquals(listString.size(),5);
        assertEquals("строка 5", listString.get(4));
    }

    @Test
    void addAllCollection() {
        initTestList();
        ArrayList<String> aList = new ArrayList<>();
        aList.add("строка 4");
        aList.add("строка 5");
        listString.addAll(aList);
        assertEquals("строка 5", listString.get(4));
    }

    @Test
    void addAllbyIndex() {
        initTestList();
        String[] list2 = new String[] {"строка 4", "строка 5"};
        listString.addAll(1,list2);
        Assertions.assertEquals(listString.size(),5);
        assertEquals("строка 3", listString.get(4));
    }

    @Test
    void get() {
        initTestList();
        assertEquals("строка 3", listString.get(2));
    }

    @Test
    void set() {
        initTestList();
        listString.set(1,"строка 4");
        assertEquals("строка 4", listString.get(1));
    }

    @Test
    void removeByIndex() {
        initTestList();
        listString.remove(1);
        Assertions.assertEquals(listString.size(),2);
        assertEquals("строка 3", listString.get(1));
    }

    @Test
    void removeByValue() {
        initTestList();
        listString.remove( "строка 1");
        Assertions.assertEquals(listString.size(),2);
        assertEquals("строка 3", listString.get(1));
    }

    @Test
    void contains() {
        initTestList();
        assertTrue(listString.contains("строка 1"));
        assertFalse(listString.contains("строка 4"));
    }

    @Test
    void indexOf() {
        initTestList();
        Assertions.assertEquals(listString.indexOf("строка 1"),0);
        Assertions.assertEquals(listString.indexOf("строка 4"),-1);
    }

    @Test
    void ensureCapacity() {
        initTestList();
        listString.ensureCapacity(12);
        Assertions.assertEquals(listString.getCapacity(),12);
    }

    @Test
    void getCapacity() {
        initTestList();
        Assertions.assertEquals(listString.getCapacity(),3);
    }

    @Test
    void reverse() {
        initTestList();
        listString.reverse();
        assertEquals("строка 1", listString.get(2));
        assertEquals("строка 3", listString.get(0));
    }

    @Test
    void toArray() {
        initTestList();
        Object[] arr = listString.toArray();
        assertEquals("строка 2", arr[1]);
    }

    @Test
    void testToString() {
        initTestList();
        Assertions.assertEquals(listString.toString(),"[ строка 1 строка 2 строка 3 ]");
    }
}
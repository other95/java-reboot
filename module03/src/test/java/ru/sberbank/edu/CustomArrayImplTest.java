package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayImplTest {

    CustomArrayImpl<String> listString;
    private void initTestList() {
        listString = new CustomArrayImpl<String>();
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
        Assertions.assertEquals(listString.isEmpty(),false);
    }

    @Test
    void add() {
      initTestList();
      listString.add("строка 4");
      Assertions.assertEquals(listString.get(3).equals("строка 4"), true);
    }

    @Test
    void addAllArray() {
        initTestList();
        String[] list2 = new String[] {"строка 4", "строка 5"};
        listString.addAll(list2);
        Assertions.assertEquals(listString.size(),5);
        Assertions.assertEquals(listString.get(4).equals("строка 5"), true);
    }

    @Test
    void addAllCollection() {
        initTestList();
        ArrayList<String> aList = new ArrayList<>();
        aList.add("строка 4");
        aList.add("строка 5");
        listString.addAll(aList);
        Assertions.assertEquals(listString.get(4).equals("строка 5"), true);
    }

    @Test
    void addAllbyIndex() {
        initTestList();
        String[] list2 = new String[] {"строка 4", "строка 5"};
        listString.addAll(1,list2);
        Assertions.assertEquals(listString.size(),5);
        Assertions.assertEquals(listString.get(4).equals("строка 3"), true);
    }

    @Test
    void get() {
        initTestList();
        Assertions.assertEquals(listString.get(2).equals("строка 3"), true);
    }

    @Test
    void set() {
        initTestList();
        listString.set(1,"строка 4");
        Assertions.assertEquals(listString.get(1).equals("строка 4"), true);
    }

    @Test
    void removeByIndex() {
        initTestList();
        listString.remove(1);
        Assertions.assertEquals(listString.size(),2);
        Assertions.assertEquals(listString.get(1).equals("строка 3"), true);
    }

    @Test
    void removeByValue() {
        initTestList();
        listString.remove( "строка 1");
        Assertions.assertEquals(listString.size(),2);
        Assertions.assertEquals(listString.get(1).equals("строка 3"), true);
    }

    @Test
    void contains() {
        initTestList();
        Assertions.assertEquals(listString.contains("строка 1"),true);
        Assertions.assertEquals(listString.contains("строка 4"),false);
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
        Assertions.assertEquals(listString.get(2).equals("строка 1"), true);
        Assertions.assertEquals(listString.get(0).equals("строка 3"), true);
    }

    @Test
    void toArray() {
        initTestList();
        Object[] arr = listString.toArray();
        Assertions.assertEquals(arr[1].equals("строка 2"), true);
    }

    @Test
    void testToString() {
        initTestList();
        Assertions.assertEquals(listString.toString(),"[ строка 1 строка 2 строка 3 ]");
    }
}
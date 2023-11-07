package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CustomDigitComparatorTest {
    @Test
    void compare() {
        Comparator<Integer> custDigitComp = new CustomDigitComparator();
        Integer a = 2;
        Integer b = 3;
        assertEquals(custDigitComp.compare(a,b),-1);
        a = 2;
        b = 4;
        assertEquals(custDigitComp.compare(a,b),-1);
        a = 5;
        b = 3;
        assertEquals(custDigitComp.compare(a,b),1);
        a = 4;
        b = 4;
        assertEquals(custDigitComp.compare(a,b),0);
        a = 4;
        b = null;
        assertEquals(custDigitComp.compare(a,b),-1);
    }
}
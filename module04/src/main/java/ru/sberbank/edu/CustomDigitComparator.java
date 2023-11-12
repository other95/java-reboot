package ru.sberbank.edu;

import java.util.Comparator;

/***
 * Пользовательский цифровой сравниватель
 * Четное всегда меньше нечетного в остальнных случаях математические правила
 */
public class CustomDigitComparator implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {

        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
          return -1;
        }
        if (o1.equals(o2)) {
            return 0;
        }
        if  ( o1 %2 == 0 && o2 %2 != 0 )  {
            return -1;
        }
        if  ( o1 %2 != 0 && o2 %2 == 0 )  {
            return 1;
        }

        if (o1 < o2) {
            return -1;
        }
        else {
            return 1;
        }

    }

}

package ru.sberbank.edu;

import java.util.Collection;

/***
 * Простой массив чтобы сохранять разные объекты
 * @param <T>
 */
public class CustomArrayImpl<T> implements CustomArray {

    private int capacity = 2;
    final private int enlargeCapacity = 1;
    private T[] items = (T[])(new Object[capacity]);
    private int numberOfItems;

    @Override
    public int size() {
        return this.numberOfItems;
    }
    @Override
    public boolean isEmpty() {
        return (numberOfItems == 0);
    }

    /***
     * Добавить один эелемент
     * @param item
     * @return
     */
    @Override
    public boolean add(Object item) {

        if (numberOfItems == this.capacity) {
            ensureCapacity(this.capacity + enlargeCapacity);
        }
        items[numberOfItems] = (T)item;
        numberOfItems++;
        return true;
    }

    /***
     * Добавить все эелементы
     * @param items
     * @return
     * @throws IllegalArgumentException если паратетр items  это null
     */
    @Override
    public boolean addAll(Object[] items) throws IllegalArgumentException {
        for (Object item:items) {
            this.add(item);
        }
        return true;
    }

    /***
     * Добавить все эелементы
     * @param items
     * @return
     * @throws IllegalArgumentException если параметр items  это null
     */
    @Override
    public boolean addAll(Collection items) throws IllegalArgumentException {
        for (Object item:items) {
            this.add(item);
        }
        return true;
    }

    /***
     * Добавить все эелементы в конкретное место массива.
     * @param index - index
     * @param items - элементы для добавления
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @throws IllegalArgumentException       if parameter items is null
     */
    @Override
    public boolean addAll(int index, Object[] items) throws ArrayIndexOutOfBoundsException,IllegalArgumentException {
        // TODO : Реализовать
        T[] arr = (T[])(new Object[this.capacity + items.length]);
        int j = 0;
        for (int i = 0; i< this.items.length; i++) {
            if ( i != index){
                arr[j] = this.items[i];
                j++;
            }
            else {
                for (int k = 0; k<items.length; k++) {
                    arr[j] = (T)items[k];
                    j++;
                }
                arr[j] = this.items[i];
                j++;
            }
        }
        this.capacity = this.capacity + items.length;
        this.numberOfItems = j;
        this.items = arr;
        return true;
    }

    /***
     * Получить значение по индексу.
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public Object get(int index) throws ArrayIndexOutOfBoundsException {
        return this.items[index];
    }

    /***
     * Установить значение элемента в ячейку с номером index.
     * @param index - index
     * @return предыдущее значение
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public Object set(int index, Object item) throws ArrayIndexOutOfBoundsException {
        T oldValue = this.items[index];
        this.items[index] = (T)item;
        return oldValue;
    }

    /***
     * Удаление элемента по индексу
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) throws ArrayIndexOutOfBoundsException {
        T[] arr = (T[])(new Object[this.numberOfItems-1]);
        int j = 0;
        for (int i = 0; i< this.numberOfItems; i++) {
            if ( i != index){
                arr[j] = this.items[i];
                j++;
            }
        }
        numberOfItems = j;
        items = arr;
    }

    /***
     * Удаление элемента по значению. Удаление первого вхождения.
     * @param item - item
     * @return истина если эдемент удален
     */
    @Override
    public boolean remove(Object item) {
        T[] arr = (T[])(new Object[this.numberOfItems-1]);
        int j = 0;
        boolean isRemoved = false;
        for (int i = 0; i< this.numberOfItems; i++) {
            if ( !this.items[i].equals(item) || isRemoved ){
                arr[j] = this.items[i];
                j++;
            }
            else {
                isRemoved = true ;
            }
        }
        numberOfItems = j;
        items = arr;
        return isRemoved;
    }

    /***
     * Проверка что элемент существует в массиве
     * @param item - элемент
     * @return истина или ложь
     */
    @Override
    public boolean contains(Object item) {
        for (Object element:items) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /***
     * Индекс элемента
     * @param item - элемент
     * @return индекс элемента или -1 если массив не содержит эелемент
     */
    @Override
    public int indexOf(Object item) {
        for (int i = 0; i<this.items.length;i++) {
            if (this.items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /***
     * Увеличить вместимость если нужно, чтобы сохранить новые эелементы
     * @param newElementsCount - количество новых элементов
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        if ( newElementsCount > this.capacity ) {
            this.capacity = newElementsCount;
            T[] arr = (T[])(new Object[this.capacity]);
            System.arraycopy(items, 0, arr, 0, numberOfItems);
            items = arr;
        }

    }
    /***
     * Получить текущую вместимость
     * @return
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /***
     * Обратить
     */
    @Override
    public void reverse() {
        T[] arr = (T[])(new Object[this.capacity]);
        for (int i = this.numberOfItems-1;i>=0;i--) {
            arr[this.numberOfItems-1-i] = this.items[i];
        }
         this.items = arr;
    }

    /***
     * Получить копию CustomArrayImpl в виде массива
     * @return
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.numberOfItems];
        for (int i = 0; i< this.numberOfItems;i++) {
            arr[i] = this.items[i];
        }
        return arr;
    }

    /***
     * Получить содержимое в формате '[ element1 element2 ... elenentN ] или [ ] если массив пуст.
     * @return
     */
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i< this.numberOfItems; i++) {
            result = result + " "+ this.items[i] ;
        }
        result = result + " ]";
        return  result;
    }

}

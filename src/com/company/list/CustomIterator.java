package com.company.list;

import java.util.Iterator;

/**
 * Класс описывающий итератор,
 * для перемещения (итерирования) по списку
 *
 * @param <E> тип элементов списка
 */
public class CustomIterator<E> implements Iterator<E> {

    private Node<E> current;

    public CustomIterator(List<E> list) {
        this.current = list.getHead();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E data = current.data;
        current = current.next;
        return data;
    }
}

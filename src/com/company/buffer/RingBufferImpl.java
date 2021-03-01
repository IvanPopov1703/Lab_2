package com.company.buffer;

import com.company.list.List;

import java.util.Iterator;

/**
 * Класс описывающий кольцевой буфер
 * на основе линейного списка
 *
 * @param <E> тип элементов буфера
 */
public class RingBufferImpl<E> implements RingBuffer<E> {

    private final int SIZE_BUF = 4; //Размер буфера

    private List list; //Список с элементами
    private int head;  //Начало очереди
    private int tail;  //Конец очереди


    /**
     * Конструктор без параметров
     */
    public RingBufferImpl() {
        clear();
    }

    /**
     * Очистка буфера
     */
    public void clear() {
        list = new List();
        head = 0;
        tail = head;
    }

    /**
     * Печать буфера
     */
    public void print() {
        list.printList(head, tail);
    }

    /**
     * Получение элемента, который будет удалён (перезаписан)
     * при вызове метода add().
     *
     * @return удаляемый элемент или если очередь не заполнена, то null
     */
    public E getLastItem() {
        int count = list.count();
        if (count < SIZE_BUF) {
            return null;
        } else {
            if (count == SIZE_BUF && tail == SIZE_BUF) {
                return (E) list.getElemByIndex(1);
            } else {
                return (E) list.getElemByIndex(tail + 1);
            }
        }
    }

    /**
     * Возвращает и удаляет элемент из начала очереди.
     *
     * @return Элемент или {@code null}, если очередь пуста
     */
    @Override
    public E poll() {
        if (!list.isEmpty()) {
            E tmpElem = (E) list.getElemByIndex(head);
            if (tail == head) { //Если в буфере один элемент
                clear();
            } else {
                if (head > tail && head == SIZE_BUF) {
                    list.remove(head);
                    head = 1;
                } else {
                    list.remove(head);
                    if (tail > head) {
                        tail--;
                    }
                }
                if (head > list.count()) {
                    head = 1;
                }
            }
            return tmpElem;
        }
        return null;
    }

    /**
     * Возвращает (но не удаляет) элемент из начала очереди.
     *
     * @return Элемент или {@code null}, если очередь пуста
     */
    @Override
    public E peek() {
        return (E) list.getElemByIndex(head);
    }

    /**
     * Добавляет элемент в конец очереди.
     * Затирает начало очереди в случае, если она заполнена.
     *
     * @param item новый элемент
     */
    @Override
    public void add(E item) {
        if (list.isEmpty()) {
            head++;
            tail = head;
            list.addInBegin(item);
            return;
        }

        if (tail == head) { //Если в списке только один элемент
            tail++;
        } else {
            if (tail < head) { //Если начало впереди конца
                tail++;
                if (tail == head && head < SIZE_BUF) {
                    head++;
                } else {
                    if (head == SIZE_BUF) {
                        head = 1;
                    }
                }
            } else {
                if (tail >= SIZE_BUF) {
                    tail = 1;
                    head++;
                } else {
                    tail++;
                }
            }
        }
        if (tail < head && list.count() < SIZE_BUF) {
            list.insertAfterIndex((tail - 1), item);
        } else {
            list.addByIndex(tail, item);
        }
    }

    /**
     * Возвращает размер коллекции.
     *
     * @return размер
     */
    @Override
    public int getSize() {
        return SIZE_BUF;
    }

    /**
     * Возвращает текущее количество элементов в буфере
     *
     * @return текущее количество элементов
     */
    @Override
    public int getCurrentNumberItems() {
        return list.count();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}

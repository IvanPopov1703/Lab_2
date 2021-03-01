package com.company.list;

import com.company.exception.AddElementException;

import java.util.Iterator;

/*
 *
 * Класс описывающий структуру данных "Линейный список"
 * и методы для работы с ним
 *
 * @param <E> тип элементов списка
 * */
public class List<E> implements Iterable<E> {

    private Node head; //Указатель на голову списка
    private Node tail; //Указатель на последний элемент спивка

    /**
     * Конструктор без параметров
     */
    public List() {
        clear();
    }

    /**
     * Очистка списка
     */
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * Проверка на пустоту
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Количество элементов в списке
     *
     * @return количество элементов
     */
    public int count() {
        if (isEmpty()) {
            return 0;
        }
        Node tmpHead = head;
        int i = 0;
        while (tmpHead != null) {
            tmpHead = tmpHead.next;
            i++;
        }
        return i;
    }

    /**
     * Печать списка
     *
     * @param start номер с которого печатать
     * @param end   номер по какой печатать
     */
    public void printList(int start, int end) {
        if (isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        System.out.println("Содержимое списка:");
        int len = count();
        for (int i = 0; i < len; i++) {
            if (start == end) {
                System.out.print(getElemByIndex(start) + "; ");
                break;
            } else {
                if (start == len && start != end) {
                    System.out.print(getElemByIndex(start) + "; ");
                    start = 1;
                } else {
                    System.out.print(getElemByIndex(start) + "; ");
                    start++;
                }
            }
        }
    }

    /**
     * Добавление элемента в начало списка
     *
     * @param node добавляемый элемент
     * @return элемент на место которого он становится
     */
    public E addInBegin(E node) {
        Node tmpElem = new Node(node);
        E el = null;
        if (isEmpty()) {
            head = tmpElem;
            tail = head;
        } else {
            el = (E) head.data;
            tmpElem.next = head;
            head = tmpElem;
        }
        return el;
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param node добавляемый элемент
     */
    public void addInEnd(E node) {
        if (isEmpty()) {
            addInBegin(node);
        } else {
            Node tmpEl = new Node(node);
            tail.next = tmpEl;
            tail = tmpEl;
        }
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param index номер позиции
     * @param node  добавляемый элемент
     * @return элемент на место которого он становится
     * @throws AddElementException индекс не корректный
     */
    public E addByIndex(int index, E node) throws AddElementException {
        if ((count() + 1) < index) {
            throw new AddElementException(index);
        }
        if (isEmpty()) {
            return addInBegin(node);
        }
        Node tmpHead = head;
        int i = 1;
        while (tmpHead != null && i < index) {
            tmpHead = tmpHead.next;
            i++;
        }
        if (tmpHead == null) {
            tmpHead = new Node(node);
            tail.next = tmpHead;
            tail = tmpHead;
            return null;
        } else {
            E returnElem = (E) tmpHead.data;
            tmpHead.data = node;
            return returnElem;
        }
    }

    /**
     * Получение элемента по номеру
     *
     * @param index номер получаемого элемента
     * @return требующийся элемент или null
     */
    public E getElemByIndex(int index) {
        if (isEmpty() || index > count()) {
            return null;
        }

        Node tmpHead = head;
        int i = 1;
        while (tmpHead != null && i < index) {
            tmpHead = tmpHead.next;
            i++;
        }

        if (tmpHead != null) {
            return (E) tmpHead.data;
        }
        return null;
    }

    /**
     * Вставка элемента после элемента с заданным индексом
     *
     * @param index индекс элемента после которого осуществлять вставку
     * @param item  добавляемы элемент
     */
    public void insertAfterIndex(int index, E item) {
        if ((count() + 1) < index) {
            throw new AddElementException(index);
        }

        Node tmpHead = head;
        Node newItem = new Node(item);
        int i = 1;
        while (tmpHead != null && i < index) {
            tmpHead = tmpHead.next;
            i++;
        }

        if (tmpHead != null) {
            newItem.next = tmpHead.next;
            tmpHead.next = newItem;
        }
    }

    /**
     * Удаление элемента по номеру
     *
     * @param index индекс удаляемого элемента
     */
    public void remove(int index) {
        if (!isEmpty()) {
            if (head == tail) {
                clear();
                return;
            }
            if (1 == index) {
                head = head.next;
                return;
            }
            Node tmpHead = head;
            Node prev = tmpHead;
            int i = 1;
            while (tmpHead != null && i < index) {
                prev = tmpHead;
                tmpHead = tmpHead.next;
                i++;
            }
            if (tmpHead != null && tmpHead.next != null) {
                prev.next = tmpHead.next;
            } else {
                if (tmpHead != null) {
                    prev.next = null;
                    tail = prev;
                }
            }
        }
    }

    /**
     * Получение указателя на голову списка
     *
     * @return указатель на голову списка
     */
    public Node<E> getHead() {
        return head;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<E>(this);
    }
}
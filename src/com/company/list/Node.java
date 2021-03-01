package com.company.list;

/*
 *
 * Класс описывающий узел для линейного списка
 *
 * @param <E> тип элементов списка
 * */
public class Node<E> {

    E data;         //Данные
    Node<E> next;   //Указатель на следующий элемент списка

    /**
     * Конструктор без параметров
     */
    public Node() {
    }

    /**
     * Конструктор с параметрами
     *
     * @param data поле данных в списке
     */
    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}

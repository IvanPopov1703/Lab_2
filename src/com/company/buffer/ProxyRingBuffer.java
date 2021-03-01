package com.company.buffer;

import java.util.Iterator;

/**
 * Proxy класс для кольцевого буфера
 *
 * @param <E> тип элементов буфера
 */
public class ProxyRingBuffer<E> implements RingBuffer<E> {

    private final RingBuffer proxy;
    private boolean isBufferFull;
    private StringBuilder strFullBuffer;

    public ProxyRingBuffer(RingBuffer ringBuffer) {
        this.proxy = ringBuffer;
        this.isBufferFull = false;
        this.strFullBuffer = new StringBuilder();
    }

    @Override
    public E poll() {
        long start = System.nanoTime();
        if (getCurrentNumberItems() == 0) {
            if (isBufferFull) {
                System.out.println("Содержимое буффера в последний момент, когда он был наполнен:");
                System.out.println(strFullBuffer.toString());
            } else {
                System.out.println("Буфер ещё ни разу не был заполнен полностью!");
            }
        }
        E resElem = (E) proxy.poll();
        System.out.println("Затрачено наносекнд на выполнение метода - "
                + (System.nanoTime() - start));
        return resElem;
    }

    @Override
    public E peek() {
        return (E) proxy.peek();
    }

    @Override
    public void add(E item) {
        long start = System.nanoTime();
        E resElem = (E) proxy.getLastItem();
        proxy.add(item);
        if (proxy.getCurrentNumberItems() == proxy.getSize()) {
            isBufferFull = true;
            strFullBuffer.delete(0, strFullBuffer.length());
            for (Object el : proxy) {
                strFullBuffer.append(el.toString() + "; ");
            }
        }
        System.out.println("Удаляемый элемент при добавлении - " + resElem
                + ";\n Затрачено наносекнд - " + (System.nanoTime() - start));
    }

    @Override
    public E getLastItem() {
        return (E) proxy.getLastItem();
    }

    @Override
    public int getSize() {
        return proxy.getSize();
    }

    @Override
    public int getCurrentNumberItems() {
        return proxy.getCurrentNumberItems();
    }

    @Override
    public void print() {
        proxy.print();
    }

    @Override
    public Iterator<E> iterator() {
        return proxy.iterator();
    }
}

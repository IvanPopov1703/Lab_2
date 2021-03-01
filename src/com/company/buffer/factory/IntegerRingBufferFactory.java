package com.company.buffer.factory;

import com.company.buffer.ProxyRingBuffer;
import com.company.buffer.RingBufferImpl;

/**
 * Класс фабрики, служащий для создания буфера,
 * элементами которого будут объекты типа Integer
 */
public class IntegerRingBufferFactory<E> implements CreateRingBufferFactory<Integer> {

    /**
     * Метод создающий буфер элементами которого
     * будут объекты типа Integer
     *
     * @return буфер типа Integer
     */
    @Override
    public ProxyRingBuffer<Integer> createRingBuffer() {
        return new ProxyRingBuffer<>(new RingBufferImpl<Integer>());
    }
}

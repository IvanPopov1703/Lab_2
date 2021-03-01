package com.company.buffer.factory;

import com.company.buffer.ProxyRingBuffer;
import com.company.buffer.RingBufferImpl;

/**
 * Класс фабрики, служащий для создания буфера,
 * элементами которого будут объекты типа String
 */
public class StringRingBufferFactory<E> implements CreateRingBufferFactory<String> {

    /**
     * Метод создающий буфер элементами которого
     * будут объекты типа String
     *
     * @return буфер типа String
     */
    @Override
    public ProxyRingBuffer<String> createRingBuffer() {
        return new ProxyRingBuffer<>(new RingBufferImpl<String>());
    }
}

package com.company.buffer.factory;

import com.company.buffer.ProxyRingBuffer;
import com.company.buffer.RingBufferImpl;

/**
 * Интерфейс фабрики
 *
 * @param <E> тип объекта
 */
public interface CreateRingBufferFactory<E> {

    /**
     * Метод для создания определённого объекта
     * класса RingBuffer
     * @return объект класса RingBuffer с определённым типом
     */
    ProxyRingBuffer<E> createRingBuffer();
}

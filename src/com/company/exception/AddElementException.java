package com.company.exception;

public class AddElementException extends RuntimeException {
    public AddElementException(int index){
        super("Невозможно добавить элемент на позицию " + index);
    }
}

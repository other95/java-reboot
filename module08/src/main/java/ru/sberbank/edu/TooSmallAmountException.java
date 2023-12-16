package ru.sberbank.edu;

public class TooSmallAmountException extends  Exception {

        public TooSmallAmountException(String message) {
            super(message);
        }
}

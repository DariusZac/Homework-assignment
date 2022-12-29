package com.movie.wiki.exception;

public class IdNotFound extends RuntimeException{
    public IdNotFound(String msg){
        super(msg);
    }
}

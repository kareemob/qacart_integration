package com.qacart.tasky.fixtures;


import com.qacart.tasky.models.Todo;

public final class TodoFixture {
    private TodoFixture(){}

    public static Todo generateTodo(){
        return Todo.builder()
                .title("Read a book")
                .build();
    }
}

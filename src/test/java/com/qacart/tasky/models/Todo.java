package com.qacart.tasky.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Todo {
    //Request
    private String title;
    private Boolean isCompleted;
    //Response
    private String message;
    private String todoId;

}

package com.xzy.guava.eventbus.simpledemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage implements Serializable {

    private String message;

}

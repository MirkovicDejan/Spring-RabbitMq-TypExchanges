package org.example.microservice2.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {
    private int id;
    private String context;
}

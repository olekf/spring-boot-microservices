package org.sandbox.speaker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Speaker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Speaker() {
    }

    public Speaker(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package demo.devsu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class Persistencia {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Getter
     @Setter
     private int id;

}

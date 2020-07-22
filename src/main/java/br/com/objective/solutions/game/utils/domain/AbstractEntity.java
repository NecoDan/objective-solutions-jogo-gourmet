package br.com.objective.solutions.game.utils.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@EqualsAndHashCode(of = "id")
public class AbstractEntity implements Serializable {
    private Long id;
    private String nome;
}

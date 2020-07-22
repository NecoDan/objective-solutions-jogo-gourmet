package br.com.objective.solutions.game.model;

import br.com.objective.solutions.game.utils.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import static br.com.objective.solutions.game.utils.RandomicoUtil.gerarValorRandomicoLong;

@SuperBuilder
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Prato extends AbstractEntity {

    public void gerarID(){
        this.setId(gerarValorRandomicoLong());
    }

    public Prato geraID(){
        gerarID();
        return this;
    }
}

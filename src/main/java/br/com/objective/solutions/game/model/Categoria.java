package br.com.objective.solutions.game.model;

import br.com.objective.solutions.game.utils.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

import static br.com.objective.solutions.game.utils.RandomicoUtil.gerarValorRandomicoLong;

@SuperBuilder
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Categoria extends AbstractEntity {

    public void gerarID() {
        this.setId(gerarValorRandomicoLong());
    }

    public Categoria geraID() {
        gerarID();
        return this;
    }

    public boolean isExists(Categoria otherCategoria) {
        return (Objects.nonNull(otherCategoria)
                && this.isNomeValido()
                && otherCategoria.isNomeValido()
                && this.getNome().equalsIgnoreCase(otherCategoria.getNome()));
    }

    private boolean isNomeValido() {
        return (Objects.nonNull(this.getNome()) && !this.getNome().isEmpty());
    }
}

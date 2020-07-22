package br.com.objective.solutions.game.service.generate;

import br.com.objective.solutions.game.utils.domain.AbstractEntity;

import java.util.List;
import java.util.function.Function;

public class IteratorService<T extends AbstractEntity> {

    public T percorrer(List<T> items, Function<T, Boolean> perguntador) {
        for (T item : items) {
            boolean resposta = perguntador.apply(item);
            if (resposta)
                return (T) item;
        }
        return null;
    }
}

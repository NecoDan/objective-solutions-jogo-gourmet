package br.com.objective.solutions.game.service.factory;

import br.com.objective.solutions.game.model.Categoria;
import br.com.objective.solutions.game.model.CategoriaOutrosBuilder;
import br.com.objective.solutions.game.model.Prato;

import java.util.*;

public final class Fabrica {

    private Fabrica() {
    }

    public static Map<Categoria, List<Prato>> gerarMapIndices() {
        Map<Categoria, List<Prato>> map = new HashMap<>();

        map.put(Categoria.builder().nome("Massa").build(), new LinkedList<>(Arrays.asList(
                Prato.builder().nome("Lasanha").build().geraID(),
                Prato.builder().nome("Macarronada").build().geraID())));
        map.put(CategoriaOutrosBuilder.newBuilder().buildCategoriaOutros(), new LinkedList<>(Arrays.asList(
                Prato.builder().nome("Bolo de chocolate").build().geraID(),
                Prato.builder().nome("Bolo de cenoura").build().geraID())));

        return map;
    }
}

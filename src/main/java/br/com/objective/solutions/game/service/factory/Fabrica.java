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

        Categoria categoriaMassa = Categoria.builder().nome("Massa").build().geraID().inicializaPilha();
        Prato pratoItemMassa = Prato.builder().nome("Macarronada").build().geraID();
        categoriaMassa.getPilha().empilhar(pratoItemMassa);

        map.put(categoriaMassa, new LinkedList<>(Collections.singletonList(pratoItemMassa)));
        map.put(CategoriaOutrosBuilder.newBuilder().buildCategoriaOutros(), new LinkedList<>(Collections.singletonList(
                Prato.builder().nome("Bolo de chocolate").build().gerarIDComValor())));

        return map;
    }
}

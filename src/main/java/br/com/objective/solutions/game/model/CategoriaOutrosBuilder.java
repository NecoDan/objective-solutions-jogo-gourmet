package br.com.objective.solutions.game.model;

public final class CategoriaOutrosBuilder {

    private final Categoria categoria;

    private CategoriaOutrosBuilder() {
        this.categoria = Categoria.builder().build();
        this.categoria.setId(1L);
    }

    public static CategoriaOutrosBuilder newBuilder() {
        return new CategoriaOutrosBuilder();
    }

    public Categoria buildCategoriaOutros() {
        this.categoria.setNome("Outros");
        return this.categoria;
    }
}

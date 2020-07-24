package br.com.objective.solutions.game.model;

public final class CategoriaOutrosBuilder {

    private final Categoria categoria;

    private CategoriaOutrosBuilder() {
        this.categoria = Categoria.builder().build();
        this.categoria.setId(1L);
        this.categoria.inicializarPilha();
        this.categoria.getPilha().empilhar(Prato.builder().nome("Bolo de chocolate").build().gerarIDComValor());
    }

    public static CategoriaOutrosBuilder newBuilder() {
        return new CategoriaOutrosBuilder();
    }

    public Categoria buildCategoriaOutros() {
        this.categoria.setNome("Bolo de Chocolate");
        return this.categoria;
    }
}

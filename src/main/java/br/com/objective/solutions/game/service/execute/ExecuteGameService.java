package br.com.objective.solutions.game.service.execute;

import br.com.objective.solutions.game.model.Categoria;
import br.com.objective.solutions.game.model.CategoriaOutrosBuilder;
import br.com.objective.solutions.game.model.Prato;
import br.com.objective.solutions.game.service.generate.AcaoService;
import br.com.objective.solutions.game.service.generate.IAcaoService;
import br.com.objective.solutions.game.service.generate.IteratorService;
import br.com.objective.solutions.game.utils.exceptions.ServiceException;
import br.com.objective.solutions.game.utils.views.Messages;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExecuteGameService implements IExecuteGameService {

    private final IteratorService<Categoria> iteratorServiceCategorias;
    private final IteratorService<Prato> iteratorServicePratos;
    private final IAcaoService acaoAoEncontrar;
    private final Categoria categoriaOutros;

    public ExecuteGameService() {
        this.iteratorServiceCategorias = new IteratorService<>();
        this.iteratorServicePratos = new IteratorService<>();
        this.categoriaOutros = CategoriaOutrosBuilder.newBuilder().buildCategoriaOutros();
        this.acaoAoEncontrar = () -> Messages.exibirMensagem("Acertei de novo!");
    }

    @Override
    public void executarGame(Map<Categoria, List<Prato>> indicesMapProdutosPorCategoria) throws ServiceException {
        Categoria chaveIndiceCategoria = getCategoriaPor(indicesMapProdutosPorCategoria);
        Optional<Prato> pratoSelecionado = getOptionalPratoPor(indicesMapProdutosPorCategoria, chaveIndiceCategoria);

        (pratoSelecionado.isPresent()
                ? this.acaoAoEncontrar
                : new AcaoService(indicesMapProdutosPorCategoria, chaveIndiceCategoria)).executar();
    }

    private Categoria getCategoriaPor(Map<Categoria, List<Prato>> indicesMapProdutosPorCategoria) {
        List<Categoria> itensParaPerguntar = getCategoriaItensParaPerguntar(indicesMapProdutosPorCategoria);
        Categoria retorno = this.iteratorServiceCategorias.percorrer(itensParaPerguntar, Messages::perguntarPorItemAbstractEntity);
        return Optional.ofNullable(retorno).orElse(this.categoriaOutros);
    }

    private Optional<Prato> getOptionalPratoPor(Map<Categoria, List<Prato>> indicesMapProdutosPorCategoria, Categoria chaveIndiceCategoria) {
        List<Prato> pratosParaPerguntar = indicesMapProdutosPorCategoria.get(chaveIndiceCategoria);
        return Optional.ofNullable(this.iteratorServicePratos.percorrer(pratosParaPerguntar, Messages::perguntarPorItemAbstractEntity));
    }

    private List<Categoria> getCategoriaItensParaPerguntar(Map<Categoria, List<Prato>> indicesMapProdutosPorCategoria) {
        return indicesMapProdutosPorCategoria
                .keySet()
                .stream()
                .filter(this::isNotCategoriaOutros)
                .collect(Collectors.toList());
    }

    private boolean isNotCategoriaOutros(Categoria categoria) {
        return (Objects.nonNull(categoria) && !Objects.equals(this.categoriaOutros, categoria));
    }
}

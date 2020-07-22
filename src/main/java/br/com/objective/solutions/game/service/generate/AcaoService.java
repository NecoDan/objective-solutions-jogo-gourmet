package br.com.objective.solutions.game.service.generate;

import br.com.objective.solutions.game.model.Categoria;
import br.com.objective.solutions.game.model.CategoriaOutrosBuilder;
import br.com.objective.solutions.game.model.Prato;
import br.com.objective.solutions.game.utils.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.objective.solutions.game.utils.views.Messages.perguntarComEntrada;

public class AcaoService implements IAcaoService {

    private final Map<Categoria, List<Prato>> indices;
    private final Categoria categoriaAtual;
    private final Categoria categoriaOutros;

    public AcaoService(Map<Categoria, List<Prato>> indices, Categoria categoriaAtual) {
        this.indices = indices;
        this.categoriaAtual = categoriaAtual;
        this.categoriaOutros = CategoriaOutrosBuilder.newBuilder().buildCategoriaOutros();
    }

    @Override
    public void executar() throws ServiceException {
        try {
            String nomeDoPrato = perguntarComEntrada("Qual eh o seu prato?");
            Categoria chaveIndiceCategoria = (isCategoriaOutros()) ? getCreateCategoriaFrom() : getCategoriaExistente();

            List<Prato> pratos = this.indices.getOrDefault(chaveIndiceCategoria, new ArrayList<>());
            pratos.add(Prato.builder().nome(nomeDoPrato).build().geraID());
            this.indices.put(chaveIndiceCategoria, pratos);
        } catch (Exception e) {
            throw new ServiceException(e.getLocalizedMessage());
        }
    }

    private Categoria getCreateCategoriaFrom() {
        String nomeCategoriaDoNovoPrato = perguntarComEntrada("Qual a categoria do seu prato?");
        Categoria categoriaDoNovoPrato = Categoria.builder().nome(nomeCategoriaDoNovoPrato).build().geraID();

        return indices.keySet()
                .stream()
                .filter(categoria -> categoria.isExists(categoriaDoNovoPrato))
                .findFirst()
                .orElse(categoriaDoNovoPrato);
    }

    private Categoria getCategoriaExistente() {
        return this.categoriaAtual;
    }

    private boolean isCategoriaOutros() {
        return this.categoriaAtual.equals(this.categoriaOutros);
    }
}

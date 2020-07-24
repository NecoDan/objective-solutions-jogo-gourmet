package br.com.objective.solutions.game.service.generate;

import br.com.objective.solutions.game.model.Categoria;
import br.com.objective.solutions.game.model.Prato;
import br.com.objective.solutions.game.utils.exceptions.ServiceException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static br.com.objective.solutions.game.utils.views.Messages.perguntarComEntrada;

public class AcaoService implements IAcaoService {

    private final Map<Categoria, List<Prato>> indices;
    private final Categoria categoriaAtual;

    public AcaoService(Map<Categoria, List<Prato>> indices, Categoria categoriaAtual) {
        this.indices = indices;
        this.categoriaAtual = categoriaAtual;
    }

    @Override
    public void executar() throws ServiceException {
        try {
            String nomePrato = obterNomePrato();
            Prato prato = Prato.builder().nome(nomePrato).build().geraID();

            Categoria chaveIndiceCategoria = getCreateCategoriaFrom(nomePrato, prato);
            chaveIndiceCategoria.getPilha().empilhar(prato);

            List<Prato> pratos = this.indices.getOrDefault(chaveIndiceCategoria, new ArrayList<>());
            pratos.add(prato);
            this.indices.put(chaveIndiceCategoria, pratos);
        } catch (Exception e) {
            throw new ServiceException(e.getLocalizedMessage());
        }
    }

    private String obterNomePrato() {
        String nomePrato = "";

        do {
            nomePrato = perguntarComEntrada("Qual prato voce pensou?");
            if (isNomePratoResultOptionEntradaInValido(nomePrato))
                JOptionPane.showMessageDialog(null, "Insira um nome de um prato válido", "Atenção", JOptionPane.WARNING_MESSAGE);
        } while (isNomePratoResultOptionEntradaInValido(nomePrato));

        return nomePrato;
    }

    private boolean isNomePratoResultOptionEntradaInValido(String nomePrato) {
        return (Objects.isNull(nomePrato) || nomePrato.isEmpty());
    }

    private Categoria getCreateCategoriaFrom(String nomePrato, Prato pratoParam) {
        Prato prato = (Prato) this.categoriaAtual.getPilha().desempilhar();
        String nomePratoAnterior = (Objects.isNull(prato)) ? " " : prato.getNome();

        String nomeCategoriaNovoPrato = perguntarComEntrada(nomePrato.concat(" eh ________ mas ".concat(nomePratoAnterior)).concat(" não."));

        return indices.keySet()
                .stream()
                .filter(categoria -> categoria.isExists(Categoria.builder().nome(nomeCategoriaNovoPrato).build()))
                .findFirst()
                .orElse(obterCategoriaNovoPrato(nomeCategoriaNovoPrato, pratoParam));
    }

    private Categoria obterCategoriaNovoPrato(String nomeCategoriaNovoPrato, Prato pratoParam) {
        Categoria categoriaNovoPrato = Categoria.builder()
                .nome(nomeCategoriaNovoPrato)
                .build()
                .geraID()
                .inicializaPilha();

        categoriaNovoPrato.getPilha().empilhar(pratoParam);
        return categoriaNovoPrato;
    }
}

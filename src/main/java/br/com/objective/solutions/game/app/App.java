package br.com.objective.solutions.game.app;

import br.com.objective.solutions.game.model.Categoria;
import br.com.objective.solutions.game.model.Prato;
import br.com.objective.solutions.game.service.execute.ExecuteGameService;
import br.com.objective.solutions.game.utils.exceptions.ServiceException;
import br.com.objective.solutions.game.utils.views.Messages;

import java.util.List;
import java.util.Map;

import static br.com.objective.solutions.game.service.factory.Fabrica.gerarMapIndices;

public class App {

    public static void main(String[] args) throws ServiceException {
        boolean continueExecute;

        ExecuteGameService executeGameGameService = new ExecuteGameService();
        Map<Categoria, List<Prato>> indicesMapProdutosPorCategoria = gerarMapIndices();

        do {
            Messages.exibirMensagem("Pense num prato que vc gosta...");
            executeGameGameService.executarGame(indicesMapProdutosPorCategoria);
            continueExecute = Messages.perguntar("Deseja continuar jogando?");
        } while (continueExecute);

        System.exit(0);
    }
}

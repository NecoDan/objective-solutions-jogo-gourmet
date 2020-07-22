package br.com.objective.solutions.game.service.execute;

import br.com.objective.solutions.game.model.Categoria;
import br.com.objective.solutions.game.model.Prato;
import br.com.objective.solutions.game.utils.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface IExecuteGameService {
    void executarGame(Map<Categoria, List<Prato>> indices) throws ServiceException;
}

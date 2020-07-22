package br.com.objective.solutions.game.utils.views;

import br.com.objective.solutions.game.utils.domain.AbstractEntity;

import javax.swing.*;

public final class Messages {

    private Messages() {
    }

    public static String perguntarComEntrada(String mensagem) {
        return JOptionPane.showInputDialog(mensagem);
    }

    public static void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public static boolean perguntarPorItemAbstractEntity(AbstractEntity abstractEntityItem) {
        return perguntar(String.format("Sua comida eh %s?", abstractEntityItem.getNome()));
    }

    public static boolean perguntar(String pergunta) {
        return JOptionPane.showConfirmDialog(null, pergunta, "Jogo Gourmet V.1.0", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION;
    }
}

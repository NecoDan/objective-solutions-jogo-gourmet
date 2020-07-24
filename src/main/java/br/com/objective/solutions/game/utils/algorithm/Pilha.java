package br.com.objective.solutions.game.utils.algorithm;

import java.io.Serializable;

public class Pilha implements Serializable {

    private static final long serialVersionUID = 1L;

    public Object[] pilha;
    public int posicaoPilha;

    public Pilha() {
        this.posicaoPilha = -1;
        this.pilha = new Object[1000];
    }

    /*IS_EMPTY*/
    public boolean isPilhaVazia() {
        return this.posicaoPilha == -1;
    }

    /*IS*/
    public int tamanho() {
        return (this.isPilhaVazia()) ? 0 : this.posicaoPilha + 1;
    }

    /*TOP*/
    public Object exibeUltimoValor() {
        return (this.isPilhaVazia()) ? null : this.pilha[this.posicaoPilha];
    }

    /*POP*/
    public Object desempilhar() {
        return (isPilhaVazia()) ? null : this.pilha[this.posicaoPilha--];
    }

    /*PUSH*/
    public void empilhar(Object valor) {
        if (this.posicaoPilha < this.pilha.length - 1)
            this.pilha[++posicaoPilha] = valor;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonamento;

/**
 *
 * @author daniel
 */
public class Verificacoes {
    
    // Calcula a quantidade de processos que não executaram totalmente
    public int qtdProcessosExecutadosTotalmente(double[][] tempodechegadaXduracao, int processos) {
        int qtd = 0;
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][1] == 0) {
                qtd++;
            }
        }
        return qtd;
    }
    
    // Calcula a posição do menor até um certo tempo
    public int posMenorDuracao(int tempoatual, double[][] tempodechegadaXduracao, int processos) {
        int posMenor = posMaiorDuracao(tempodechegadaXduracao, processos);
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][1] > 0 && tempodechegadaXduracao[i][1] < tempodechegadaXduracao[posMenor][1] &&
                    tempodechegadaXduracao[i][0] < tempoatual) {
                posMenor = i;
            }
        }
        return posMenor;
    }
    
    // Calcula a posição do maior até um certo processo x
    public int posMaiorDuracaoAteX(int posx, double[][] tempodechegadaXduracao, int processos) {
        int posMaior = posMenorDuracao(tempodechegadaXduracao, processos);
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][1] != 0 && tempodechegadaXduracao[i][1] > tempodechegadaXduracao[posMaior][1] &&
                    tempodechegadaXduracao[i][0] < tempodechegadaXduracao[posx][0]) {
                posMaior = i;
            }
        }
        if (posMaior == posMenorDuracao(tempodechegadaXduracao, processos)) { // Se não entrou no if, é porque posx é menor
            return posx;
        }
        return posMaior;
    }
    
    // Calcula a posição do menor até um certo processo x
    public int posMenorDuracaoAteX(int posx, double[][] tempodechegadaXduracao, int processos) {
        int posMenor = posMaiorDuracao(tempodechegadaXduracao, processos);
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][1] != 0 && tempodechegadaXduracao[i][1] < tempodechegadaXduracao[posMenor][1] &&
                    tempodechegadaXduracao[i][0] < tempodechegadaXduracao[posx][0]) {
                posMenor = i;
            }
        }
        if (posMenor == posMaiorDuracao(tempodechegadaXduracao, processos)) { // Se não entrou no if, é porque posx é menor
            return posx;
        }
        return posMenor;
    }

    // Calcula a posição do menor duração não nula
    public int posMenorDuracao(double[][] tempodechegadaXduracao, int processos) {
        int posMenor = posMaiorDuracao(tempodechegadaXduracao, processos);
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][1] != 0 && tempodechegadaXduracao[i][1] < tempodechegadaXduracao[posMenor][1]) {
                posMenor = i;
            }
        }
        return posMenor;
    }
    
    // Calcula a posição do maior duração não nula
    public int posMaiorDuracao(double[][] tempodechegadaXduracao, int processos) {
        int posMaior = 0;
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][1] > tempodechegadaXduracao[posMaior][1]) {
                posMaior = i;
            }
        }
        return posMaior;
    }

    // Calcula a posição do menor tempo de chegada com base no menor tempo anterior
    public int posMenorTempoDeChegada(int posMenorAnterior, double[][] tempodechegadaXduracao, int processos) {
        int posMenor = posMaiorTempoDeChegada(tempodechegadaXduracao, processos);
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][0] < tempodechegadaXduracao[posMenor][0] && tempodechegadaXduracao[i][0] > tempodechegadaXduracao[posMenorAnterior][0]
                    && tempodechegadaXduracao[i][1] != 0) {
                posMenor = i;
            }
        }
        return posMenor;
    }
    
    // Calcula a posição do menor tempo de chegada não nulo
    public int posMenorTempoDeChegadaNaoNulo(int tempoatual, double[][] tempodechegadaXduracao, int processos) {
        int posMenor = posMaiorTempoDeChegada(tempodechegadaXduracao, processos);
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][0] < tempodechegadaXduracao[posMenor][0] && tempodechegadaXduracao[i][1] > 0 &&
                    tempodechegadaXduracao[i][0] < tempoatual) {
                posMenor = i;
            }
        }
        return posMenor;
    }

    // Calcula a posição do menor tempo de chegada
    public int posMenorTempoDeChegada(double[][] tempodechegadaXduracao, int processos) {
        int posMenor = 0;
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][0] < tempodechegadaXduracao[posMenor][0] && tempodechegadaXduracao[i][1] != 0) {
                posMenor = i;
            }
        }
        return posMenor;
    }

    // Calcula a posição do maior tempo de chegada
    public int posMaiorTempoDeChegada(double[][] tempodechegadaXduracao, int processos) {
        int posMaior = 0;
        // Encontra o menor valor da matriz
        for (int i = 0; i < processos; i++) {
            if (tempodechegadaXduracao[i][0] > tempodechegadaXduracao[posMaior][0]) {
                posMaior = i;
            }
        }
        return posMaior;
    }
}

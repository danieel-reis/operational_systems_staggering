/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonamento;

import Util.ImprimeMatriz;

/**
 *
 * @author daniel
 */
public class DO {

    private double[][] tempodechegadaXduracaoaux;
    private int tam_DO;
    private double[][] DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;

    public void executaDO(double[][] tempodechegadaXduracao, int processos) {
        Verificacoes verifica = new Verificacoes();

        // Instanciação
        tempodechegadaXduracaoaux = new double[processos][2];

        // Cria-se uma copia da matriz para que se possa manipula-lá
        for (int i = 0; i < processos; i++) {
            for (int j = 0; j < 2; j++) {
                tempodechegadaXduracaoaux[i][j] = tempodechegadaXduracao[i][j];
            }
        }

        double duracao = 0;
        // Encontra tempo de duracao total, pois este poderá ser o tamanho máximo da matriz
        int timemax = 0;
        for (int i = 0; i < processos; i++) {
            timemax += tempodechegadaXduracao[i][1];
        }
        DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = new double[timemax][6];

        int posMenor = 0; // De início menor vale a primeira posicao
        int posProx = 0;
        int i = 0, j = 0;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        while (i < timemax) {
            if (i == 0) { // De inicio, o primeiro processo é o que chegou primeiro
                posMenor = verifica.posMenorTempoDeChegada(tempodechegadaXduracaoaux, processos);
                posProx = verifica.posMenorTempoDeChegada(posMenor, tempodechegadaXduracaoaux, processos);
            } else { // Considerar o anterior
                if (j < processos - 1) { // Primeiro ao penultimo processo a ser executado na ordem de chegada
                    posMenor = verifica.posMenorTempoDeChegada(posMenor, tempodechegadaXduracaoaux, processos);
                    posProx = verifica.posMenorTempoDeChegada(posMenor, tempodechegadaXduracaoaux, processos); // Encontra a próxima posição a ser inserida
                    if (tempodechegadaXduracaoaux[verifica.posMaiorDuracaoAteX(posMenor, tempodechegadaXduracaoaux, processos)][1] < 
                        tempodechegadaXduracaoaux[posMenor][1]) {
                        posMenor = verifica.posMaiorDuracaoAteX(posMenor, tempodechegadaXduracaoaux, processos);
                        posProx = verifica.posMenorTempoDeChegada(posMenor, tempodechegadaXduracaoaux, processos); // Encontra a próxima posição a ser inserida
                    }
                } else if (j == processos - 1) { // Ultimo processo a ser executado na ordem de chegada
                    posMenor = verifica.posMenorTempoDeChegada(posMenor, tempodechegadaXduracaoaux, processos);
                    posProx = -1;
                    if (tempodechegadaXduracaoaux[verifica.posMaiorDuracaoAteX(posMenor, tempodechegadaXduracaoaux, processos)][1] < 
                        tempodechegadaXduracaoaux[posMenor][1]) {
                        posMenor = verifica.posMaiorDuracaoAteX(posMenor, tempodechegadaXduracaoaux, processos);
                    }
                } else { // Já executei uma vez todos os processos. Executar o resto que falta de cada processo
                    posMenor = verifica.posMaiorDuracao(tempodechegadaXduracaoaux, processos);
                    posProx = -1;
                }
            }

            // Atualiza a matriz. Se não for o último processo e se alguns processos ainda não foram executados
            if (j < processos - 1) {
                // Se o tempo de execução dele é menor ou igual a diferença entre os tempos em que os processos chegaram, ele executou todo
                if (tempodechegadaXduracaoaux[posMenor][1] <= tempodechegadaXduracaoaux[posProx][0] - tempodechegadaXduracaoaux[posMenor][0]) {
                    duracao = tempodechegadaXduracaoaux[posMenor][1]; // Duração
                } else {
                    duracao = tempodechegadaXduracaoaux[posProx][0] - tempodechegadaXduracaoaux[posMenor][0]; // Duração
                }
                tempodechegadaXduracaoaux[posMenor][1] -= duracao; // Retiro apenas o tempo que ele executou
            } else { // Se todos processos já executaram uma vez
                duracao = tempodechegadaXduracaoaux[posMenor][1]; // Duração total
                tempodechegadaXduracaoaux[posMenor][1] = 0; // Execução total 
            }

            DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][0] = posMenor; // Processo analisado
            DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][1] = tempodechegadaXduracaoaux[posMenor][0]; // Tempo de chegada do processo
            DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][2] = tempodechegadaXduracao[posMenor][1]; // Tempo de execução total
            DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][3] = tempodechegadaXduracaoaux[posMenor][1]; // Tempo de execução restante        
            DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][4] = i; // Inicio da execução
            DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][5] = i + duracao; // Fim da execução

            i += duracao; // Incrementa o i com a duração do processo
            j++; // Incrementa a posicao em que está preenchendo na matriz DO

//            ImprimeMatriz imprime = new ImprimeMatriz();
//            imprime.imprimeMatriz(tempodechegadaXduracaoaux, processos, 2, "------------------------ MATRIZ ------------------------");
        }
        tam_DO = j;
    }

    public double[][] getDOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao() {
        return DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    }

    public int gettam_DO() {
        return tam_DO;
    }
}

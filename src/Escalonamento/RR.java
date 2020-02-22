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
public class RR {

    private double[][] tempodechegadaXduracaoaux;
    private int tam_RR;
    private double[][] RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;

    public void executaRR(double[][] tempodechegadaXduracao, int processos, int quantum) {
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
        RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = new double[timemax][6];

        int posMenor = 0; // De início menor vale a primeira posicao
        int i = 0, j = 0, k = 0;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        while (i < timemax) {
//            System.out.println("k = " + k + " - qtdProcessosExecutadosTotalmente = " + verifica.qtdProcessosExecutadosTotalmente(tempodechegadaXduracaoaux, processos));
            if (k == 0) {
                posMenor = verifica.posMenorTempoDeChegada(tempodechegadaXduracao, processos);
            } else {
                posMenor = verifica.posMenorTempoDeChegada(posMenor, tempodechegadaXduracao, processos);
            }

            // Atualiza a matriz.
            // Se o tempo de execução dele é menor ou igual a diferença entre os tempos em que os processos chegaram, ele executou todo
            if (tempodechegadaXduracaoaux[posMenor][1] < quantum) {
                duracao = tempodechegadaXduracaoaux[posMenor][1]; // Duração
            } else {
                duracao = quantum; // Duração
            }

//            System.out.println("Tempo de chegada: " + tempodechegadaXduracao[posMenor][0] + " - Tempo atual: " + i);
            if (duracao > 0 && i >= tempodechegadaXduracao[posMenor][0]) { // Se o processo analisado já chegou e se tem duração não nula
                tempodechegadaXduracaoaux[posMenor][1] -= duracao; // Retiro apenas o tempo que ele executou

                RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][0] = posMenor; // Processo analisado
                RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][1] = tempodechegadaXduracaoaux[posMenor][0]; // Tempo de chegada do processo
                RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][2] = tempodechegadaXduracao[posMenor][1]; // Tempo de execução total
                RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][3] = tempodechegadaXduracaoaux[posMenor][1]; // Tempo de execução restante
                RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][4] = i; // Inicio da execução
                RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][5] = i + duracao; // Fim da execução

                i += duracao; // Incrementa o i com o quantum do processo
                j++; // Incrementa a posicao em que está preenchendo na matriz RR
            }
            k++;

//            ImprimeMatriz imprime = new ImprimeMatriz();
//            imprime.imprimeMatriz(tempodechegadaXduracaoaux, processos, 2, "------------------------ MATRIZ ------------------------");
            if (posMenor == verifica.posMaiorTempoDeChegada(tempodechegadaXduracao, processos)) { // Ultimo processo na ordem de chegada já foi executado
                k = 0;
            }
        }
        tam_RR = j;
    }

    public double[][] getRRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao() {
        return RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    }

    public int gettam_RR() {
        return tam_RR;
    }
}

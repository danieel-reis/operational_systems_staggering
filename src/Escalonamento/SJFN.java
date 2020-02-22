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
public class SJFN {

    private double[][] tempodechegadaXduracaoaux;
    private int tam_SJFN;
    private double[][] SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;

    public void executaSJFN(double[][] tempodechegadaXduracao, int processos) {
        Verificacoes verifica = new Verificacoes();

        double duracao = 0;
        // Encontra tempo de duracao total, pois este poderá ser o tamanho máximo da matriz
        int timemax = 0;
        for (int i = 0; i < processos; i++) {
            timemax += tempodechegadaXduracao[i][1];
        }
        SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = new double[processos][6];
        tempodechegadaXduracaoaux = new double[processos][2];
        
        for (int i = 0; i < processos; i++) {
            tempodechegadaXduracaoaux[i][0] = tempodechegadaXduracao[i][0];
            tempodechegadaXduracaoaux[i][1] = tempodechegadaXduracao[i][1];
        }

        int posMenor = 0; // De início menor vale a primeira posicao
        int posMenorDuracao = 0;
        int i = 0, j = 0;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        while (i < timemax) {
            if (i == 0) { // De inicio, o primeiro processo é o que chegou primeiro
                posMenor = verifica.posMenorTempoDeChegada(tempodechegadaXduracaoaux, processos);
            } else { // Primeiro ao penultimo processo a ser executado na ordem de chegada
                posMenor = verifica.posMenorTempoDeChegadaNaoNulo(i, tempodechegadaXduracaoaux, processos);
                posMenorDuracao = verifica.posMenorDuracao(i, tempodechegadaXduracaoaux, processos);
                // Se existe outra posicao com menor duração do que ele
                if (tempodechegadaXduracaoaux[posMenorDuracao][1] <= tempodechegadaXduracaoaux[posMenor][1]) {
                    posMenor = posMenorDuracao;
                }
            }

            duracao = tempodechegadaXduracaoaux[posMenor][1]; // Duração total
            tempodechegadaXduracaoaux[posMenor][1] = 0; // Marquei que o processo foi executado
//            System.out.println("Posmenor: " + posMenor + " - Duracao: " + duracao);

            SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][0] = posMenor; // Processo analisado
            SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][1] = tempodechegadaXduracao[posMenor][0]; // Tempo de chegada do processo
            SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][2] = tempodechegadaXduracao[posMenor][1]; // Tempo de execução total
            SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][3] = 0; // Tempo de execução restante         
            SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][4] = i; // Inicio da execução
            SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[j][5] = i + duracao; // Fim da execução

            i += duracao; // Incrementa o i com a duração do processo
            j++; // Incrementa a posicao em que está preenchendo na matriz SJFN

//            ImprimeMatriz imprime = new ImprimeMatriz();
//            imprime.imprimeMatriz(tempodechegadaXduracaoaux, processos, 2, "------------------------ MATRIZ ------------------------");
        }
        tam_SJFN = j;
    }

    public double[][] getSJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao() {
        return SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    }

    public int gettam_SJFN() {
        return tam_SJFN;
    }
}

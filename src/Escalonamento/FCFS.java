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
public class FCFS {
    
    private double[][] FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    private int tam_FCFS;
    
    public void executaFCFS(double[][] tempodechegadaXduracao, int processos) {
        Verificacoes verifica = new Verificacoes();
        double inicio = 0;
        FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = new double[processos][6];

        int posMenor = 0; // De início menor vale a primeira posicao
        for (int i = 0; i < processos; i++) {
            /*
            O primeiro processo não espera nada.
            O tempo de retorno e o de resposta são o tempo de execução nesse casp.
            O tempo esperado pelos demais é o tempo de execução do processo anterior.
            */
            if(i == 0) {
                posMenor = verifica.posMenorTempoDeChegada(tempodechegadaXduracao, processos);
            }
            else {
                posMenor = verifica.posMenorTempoDeChegada(posMenor, tempodechegadaXduracao, processos);
            }

            FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[i][0] = i; // Processo
            FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[i][1] = tempodechegadaXduracao[posMenor][0]; // Tempo de chegada o processo
            FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[i][2] = tempodechegadaXduracao[posMenor][1]; // Tempo de execução total
            FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[i][3] = 0; // Tempo de execução restante
            FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[i][4] = inicio;
            FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao[i][5] = inicio + tempodechegadaXduracao[posMenor][1]; // Fim de execução
            inicio += tempodechegadaXduracao[posMenor][1]; // Duração
        }
        tam_FCFS = processos;
    }

    public double[][] getFCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao() {
        return FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    }
    
    public int gettam_FCFS() {
        return tam_FCFS;
    }  
}

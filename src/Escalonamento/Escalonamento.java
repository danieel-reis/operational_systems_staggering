/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonamento;

import Graficos.BarrasEmpilhadas;
import Graficos.SerieTresLinhas;
import Util.ImprimeMatriz;
import Util.SelecionaCaminhoArquivo;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author daniel
 */
public class Escalonamento {

    private ArrayList<Double> timeretorno;
    private ArrayList<Double> timeespera;
    private ArrayList<Double> timeresposta;
    private double[][] FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    private int tam_FCFS;
    private double[][] SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    private int tam_SJFN;
    private double[][] SJFPprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    private int tam_SJFP;
    private double[][] RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    private int tam_RR;
    private double[][] DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao;
    private int tam_DO;

    public void realizaExecucao(int quantum, double[][] tempodechegadaXduracao, int processos, File arquivo) throws IOException, DocumentException {
        // Instanciação
        timeretorno = new ArrayList<Double>();
        timeespera = new ArrayList<Double>();
        timeresposta = new ArrayList<Double>();
        ImprimeMatriz imprime = new ImprimeMatriz();

        // Executa os métodos
        FCFS fcfs = new FCFS();
        fcfs.executaFCFS(tempodechegadaXduracao, processos);
        FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = fcfs.getFCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao();
        tam_FCFS = fcfs.gettam_FCFS();
        calculaTempoMedio(FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_FCFS, processos);
        // Imprime os tempos médios
        System.out.println("------------------------ FCFS ------------------------");
        System.out.println("Tempo de retorno médio: " + timeretorno.get(0));
        System.out.println("Tempo de espera médio: " + timeespera.get(0));
        System.out.println("Tempo de resposta médio: " + timeresposta.get(0));
        // Imprime a matriz gerada
        imprime.imprimeMatriz(FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_FCFS, 6, "------------------------ FCFS ------------------------");

        SJFN sjfn = new SJFN();
        sjfn.executaSJFN(tempodechegadaXduracao, processos);
        SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = sjfn.getSJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao();
        tam_SJFN = sjfn.gettam_SJFN();
        calculaTempoMedio(SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_SJFN, processos);
        // Imprime os tempos médios
        System.out.println("------------------------ SJFN ------------------------");
        System.out.println("Tempo de retorno médio: " + timeretorno.get(1));
        System.out.println("Tempo de espera médio: " + timeespera.get(1));
        System.out.println("Tempo de resposta médio: " + timeresposta.get(1));
        // Imprime a matriz gerada
        imprime.imprimeMatriz(SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_SJFN, 6, "------------------------ SJFN ------------------------");

        SJFP sjfp = new SJFP();
        sjfp.executaSJFP(tempodechegadaXduracao, processos);
        SJFPprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = sjfp.getSJFPprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao();
        tam_SJFP = sjfp.gettam_SJFP();
        calculaTempoMedio(SJFPprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_SJFP, processos);
        // Imprime os tempos médios
        System.out.println("------------------------ SJFP ------------------------");
        System.out.println("Tempo de retorno médio: " + timeretorno.get(2));
        System.out.println("Tempo de espera médio: " + timeespera.get(2));
        System.out.println("Tempo de resposta médio: " + timeresposta.get(2));
        // Imprime a matriz gerada
        imprime.imprimeMatriz(SJFPprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_SJFP, 6, "------------------------ SJFP ------------------------");

        DO doo = new DO();
        doo.executaDO(tempodechegadaXduracao, processos);
        DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = doo.getDOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao();
        tam_DO = doo.gettam_DO();
        calculaTempoMedio(DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_DO, processos);
        // Imprime os tempos médios
        System.out.println("------------------------ DO ------------------------");
        System.out.println("Tempo de retorno médio: " + timeretorno.get(3));
        System.out.println("Tempo de espera médio: " + timeespera.get(3));
        System.out.println("Tempo de resposta médio: " + timeresposta.get(3));
        // Imprime a matriz gerada
        imprime.imprimeMatriz(DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_DO, 6, "------------------------ DO ------------------------");
        
        RR rr = new RR();
        rr.executaRR(tempodechegadaXduracao, processos, quantum);
        RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao = rr.getRRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao();
        tam_RR = rr.gettam_RR();
        calculaTempoMedio(RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_RR, processos);
        // Imprime os tempos médios
        System.out.println("------------------------ RR ------------------------");
        System.out.println("Tempo de retorno médio: " + timeretorno.get(4));
        System.out.println("Tempo de espera médio: " + timeespera.get(4));
        System.out.println("Tempo de resposta médio: " + timeresposta.get(4));
        // Imprime a matriz gerada
        imprime.imprimeMatriz(RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_RR, 6, "------------------------ RR ------------------------");

        int opc;
        do {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Digite: ");
            System.out.println("1 - Gráfico de execução");
            System.out.println("2 - Gráfico de tempos ");
            System.out.println("3 - Gerar relatório");
            System.out.println("4 - Sair");
            System.out.println("Opção escolhida: ");
            Scanner in = new Scanner(System.in);
            opc = in.nextInt();

            if (opc == 1) {
                BarrasEmpilhadas demo = geraGraficoBarrasEmpilhadas();
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
            } else if (opc == 2) {
                SerieTresLinhas demo = geraGraficoTempos();
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
            } else if (opc == 3) {
                geraGraficoBarrasEmpilhadas();
                geraGraficoTempos();
                // Seleciona o local para salvar o arquivo
                SelecionaCaminhoArquivo select = new SelecionaCaminhoArquivo();
                String filePath = select.selectPaste();

                String nomedoarq;
                do {
                    nomedoarq = JOptionPane.showInputDialog(null, "Digite o nome para salvar o arquivo?", "Pergunta", JOptionPane.PLAIN_MESSAGE);
                } while (nomedoarq == null || nomedoarq == "");
                CriaPDF escreve = new CriaPDF();
                escreve.geraPdf(filePath, nomedoarq, FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_FCFS,
                        SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_SJFN,
                        SJFPprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_SJFP,
                        RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_RR,
                        DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_DO,
                        quantum, timeretorno, timeespera, timeresposta);
                EscreveTxt gera = new EscreveTxt();
                gera.grava(timeretorno, timeespera, timeresposta, arquivo.getName(), filePath);
            }
        } while (opc < 1 || opc > 4);
    }

    private BarrasEmpilhadas geraGraficoBarrasEmpilhadas() throws IOException {
        // Gera grafico de barras empilhadas
        String name_img = "grafico.png";
        int dimension_x = 625;
        int dimension_y = 400;
        String title = "Execução dos métodos";
        final BarrasEmpilhadas demo = new BarrasEmpilhadas(name_img, title, dimension_x, dimension_y, FCFSprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao,
                tam_FCFS, SJFNprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_SJFN, SJFPprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao,
                tam_SJFP, RRprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_RR, DOprocessoXtempodechegadaXtempodeexecucaototalXtempodeexecucaorestanteXiniciodeexecucaoXfimdeexecucao, tam_DO);
        return demo;
    }

    private SerieTresLinhas geraGraficoTempos() throws IOException {
        // Gera grafico de relatorio
        String name_img = "grafico1.png";
        int dimension_x = 625;
        int dimension_y = 400;
        String title = "Tempos médios";
        String nome_linha1 = "Tempo de retorno médio";
        String nome_linha2 = "Tempo de espera médio";
        String nome_linha3 = "Tempo de resposta médio";
        String nome_x = "Execução";
        String nome_y = "Segundos";
        ArrayList<String> names = new ArrayList<String>();
        names.add("FCFS");
        names.add("SJFN");
        names.add("SJFP");
        names.add("DO");
        names.add("RR");
        final SerieTresLinhas demo = new SerieTresLinhas(nome_linha1, nome_linha2, nome_linha3, name_img, title, nome_x, nome_y, names, timeretorno, timeespera, timeresposta, dimension_x, dimension_y);
        return demo;
    }

    private void calculaTempoMedio(double matriz[][], int tamanho, int processos) {
        double timerespostamedia = 0, timeesperamedia = 0, timeretornomedia = 0;

        // Media do tempo de espera
        timeesperamedia = calculaTempodeEspera(matriz, tamanho, processos) / processos;

        // Media do tempo de resposta
        timerespostamedia = calculaTempodeResposta(matriz, tamanho, processos) / processos;

        // Media do tempo de retorno
        timeretornomedia = calculaTempodeRetorno(matriz, tamanho, processos) / processos;

        // Adiciona no array para gerar o gráfico
        this.timeresposta.add(timerespostamedia);
        this.timeespera.add(timeesperamedia);
        this.timeretorno.add(timeretornomedia);
    }

    private double calculaTempodeResposta(double matriz[][], int tamanho, int processos) {
        // Instanciação
        double[][] tresposta = new double[processos][3];
        double timeresposta = 0;

        // Tempo de retorno -> Testar todas vezes que o processo ocorreu
        for (int j = 0; j < processos; j++) {
            for (int i = 0; i < tamanho; i++) {
                if (matriz[i][0] == j) { // Primeira vez que rodou o processo
                    tresposta[j][0] = matriz[i][1]; // Tempo de chegada
                    tresposta[j][1] = matriz[i][4]; // Inicio da execução
                    break;
                }
            }
            tresposta[j][2] = tresposta[j][1] - tresposta[j][0]; // Tempoderesposta = Iniciodaexecucao - tempodechegada
//            System.out.println(tresposta[j][2]);
        }
        // Somar o tempo de retorno de todos processos
        for (int i = 0; i < processos; i++) {
            timeresposta += tresposta[i][2];
            System.out.println("\ttimeresposta P"+ (i+1) + " = " + tresposta[i][2]);
        }
        System.out.println("somatimeresposta = " + timeresposta);

        return timeresposta;
    }

    private double calculaTempodeRetorno(double matriz[][], int tamanho, int processos) {
        // Instanciação
        double[][] tretorno = new double[processos][3];
        double timeretorno = 0;
        int processo;

        // Tempo de retorno -> Testar todas vezes que o processo ocorreu
        for (int j = 0; j < processos; j++) {
            processo = 0;
            for (int i = 0; i < tamanho; i++) {
                if (matriz[i][0] == j && processo == 0) { // Primeira vez que rodou o processo
                    tretorno[j][0] = matriz[i][1]; // Tempo de retorno = iniciodaexecução
                    tretorno[j][1] = matriz[i][5]; // Fim de execução
                    processo++;
                } else if (matriz[i][0] == j && processo > 0) { // A partir da primeira vez que rodou o processo
                    tretorno[j][1] = matriz[i][5]; // Fim de execução
                    processo++;
                }
            }
            tretorno[j][2] = tretorno[j][1] - tretorno[j][0]; // Fimdaexecucao - tempodechegada
        }
        // Somar o tempo de retorno de todos processos
        for (int i = 0; i < processos; i++) {
            timeretorno += tretorno[i][2];
            System.out.println("\ttimeretorno P"+ (i+1) + " = " + tretorno[i][2]);
        }
        System.out.println("somatimeretorno = " + timeretorno);

        return timeretorno;
    }

    private double calculaTempodeEspera(double matriz[][], int tamanho, int processos) {
        // Instanciação
        double[][] tespera = new double[processos][3];
        double timeespera = 0;
        int processo;

        // Tempo de espera -> Testar todas vezes que o processo ocorreu
        for (int j = 0; j < processos; j++) {
            processo = 0;
            for (int i = 0; i < tamanho; i++) {
                if (matriz[i][0] == j && processo == 0) { // Primeira vez que rodou o processo
                    tespera[j][0] = matriz[i][4]; // Inicio de execução
                    tespera[j][1] = matriz[i][5]; // Fim de execução
                    tespera[j][2] = matriz[i][4] - matriz[i][1]; // Tempo de espera = iniciodaexecução - tempodechegada
                    processo++;
                } else if (matriz[i][0] == j && processo > 0) { // A partir da primeira vez que rodou o processo
                    tespera[j][2] += matriz[i][4] - tespera[j][1]; // Tempo de espera = iniciodaexecução - tempodefimdaexecucaoanterior
                    tespera[j][0] = matriz[i][4]; // Inicio de execução
                    tespera[j][1] = matriz[i][5]; // Fim de execução
                    processo++;
                }
            }
        }
        // Somar o tempo de espera de todos processos
        for (int i = 0; i < processos; i++) {
            timeespera += tespera[i][2];
            System.out.println("\ttimeespera P"+ (i+1) + " = " + tespera[i][2]);
        }
        System.out.println("somatimeespera = " + timeespera);

        return timeespera;
    }
}

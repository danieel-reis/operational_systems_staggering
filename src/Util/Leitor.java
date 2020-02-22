package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leitor {

    private File arquivo;
    private double[][] tempodechegadaXduracao;
    private int processos;
    
    public void leituraProcessos(String name) throws FileNotFoundException, IOException {
        this.arquivo = new File(name);
        FileReader reader = new FileReader(arquivo);
        BufferedReader bufferedReader = new BufferedReader(reader);
        
        //Instanciacao
        tempodechegadaXduracao = new double[10000][2];
        
        //Preenche tempodechegada do processo x duracao do processo
        processos = 0;
        int bytelido = 0;
        
        while(bytelido < arquivo.length()) {
            String[] info = bufferedReader.readLine().split(" ");
            
            double tempodechegadadoprocesso = Double.parseDouble(info[0]);
            double duracadoprocesso = Double.parseDouble(info[1]);
            
            // Ler o tempo e duração dos processos e incrementar a quantidade de bytes lidos
            tempodechegadaXduracao[processos][0] = tempodechegadadoprocesso;
            tempodechegadaXduracao[processos][1] = duracadoprocesso;
            String t = String.valueOf(tempodechegadadoprocesso);
            String d = String.valueOf(duracadoprocesso);
            bytelido += t.length() - 1;
            bytelido += d.length() - 1;
            
            // Ler a linha vazia e incrementar a quantidade de bytes lidos
            bufferedReader.readLine();
            bytelido += 1; 
            
            // Incrementar a quantidade de processos lidos
            processos++;
            
//            System.out.println("t = " + t.length() + " - d = " + d.length());
//            System.out.println("Bytes lidos = " + bytelido + " - Processos = " + processos);
        }

        bufferedReader.close();
        reader.close();
        
        imprimeMatriz(this.tempodechegadaXduracao, this.processos); // Impressão para teste da leitura
    }
    
    public void imprimeMatriz(double matriz[][], int processos) {
        System.out.println("******************** TEMPO DE CHEGADA DO PROCESSO X DURAÇÃO DO PROCESSO ********************");
        for (int i = 0; i < processos; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public double[][] getMatriz() {
        return this.tempodechegadaXduracao;
    }
    
    public int getProcessos() {
        return this.processos;
    }
    
    public File getArquivo() {
        return this.arquivo;
    }
}
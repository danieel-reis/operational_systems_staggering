/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonamento;

import Util.ImprimeMatriz;
import Util.Leitor;
import Util.SelecionaCaminhoArquivo;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Main {

    public static void main(String args[]) throws IOException, DocumentException {
        // Instanciações
        Leitor leitor = new Leitor();
        SelecionaCaminhoArquivo select = new SelecionaCaminhoArquivo();
        Escalonamento escalonamento = new Escalonamento();

        String path = select.selectFilePath(); // Seleciona o caminho do arquivo
        leitor.leituraProcessos(path); // Realiza a leitura do arquivo e o preenchimento da matriz

        int quantum;
        do {
            System.out.println("Digite o valor do quantum: ");
            Scanner in = new Scanner(System.in);
            quantum = in.nextInt();
        } while (quantum < 1);
        escalonamento.realizaExecucao(quantum, leitor.getMatriz(), leitor.getProcessos(), leitor.getArquivo()); // Realiza as execuções
    }
}
        

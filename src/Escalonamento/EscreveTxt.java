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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EscreveTxt {
            
    public void grava(ArrayList<Double> timeretorno, ArrayList<Double> timeespera, ArrayList<Double> timeresposta, String nome, String filePath)
            throws IOException {
        String name = "";
        FileWriter arq = new FileWriter(filePath + "/" + nome.substring(0,(nome.length()-4)) + ".sol");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        // Reorganizar o array
        ArrayList<Double> timeretornoaux = new ArrayList<Double>();
        ArrayList<Double> timeesperaaux = new ArrayList<Double>();
        ArrayList<Double> timerespostaaux = new ArrayList<Double>();
        timeretornoaux.add(timeretorno.get(0)); // FCFS
        timeesperaaux.add(timeespera.get(0)); // FCFS
        timerespostaaux.add(timeresposta.get(0)); // FCFS
        timeretornoaux.add(timeretorno.get(1)); // SJFN
        timeesperaaux.add(timeespera.get(1)); // SJFN
        timerespostaaux.add(timeresposta.get(1)); // SJFN
        timeretornoaux.add(timeretorno.get(2)); // SJFP
        timeesperaaux.add(timeespera.get(2)); // SJFP
        timerespostaaux.add(timeresposta.get(2)); // SJFP
        timeretornoaux.add(timeretorno.get(4)); // RR
        timeesperaaux.add(timeespera.get(4)); // RR
        timerespostaaux.add(timeresposta.get(4)); // RR
        timeretornoaux.add(timeretorno.get(3)); // DO
        timeesperaaux.add(timeespera.get(3)); // DO
        timerespostaaux.add(timeresposta.get(3)); // DO
        
        for(int i = 0; i < timeesperaaux.size(); i++) {
            switch(i)
            {
                case 0:
                    name = "FCFS";
                    break;
                case 1:
                    name = "SJFN";
                    break;
                case 2:
                    name = "SJFP";
                    break;
                case 3:
                    name = "RR";
                    break;
                case 4:
                    name = "DO";
                    break;
            }
            
            String text = name + " " + String.valueOf(timeretornoaux.get(i)) + " ";
            text += String.valueOf(timeesperaaux.get(i)) + " ";
            text += String.valueOf(timerespostaaux.get(i));
            gravarArq.printf(text);
            gravarArq.printf("\n");
            if(i < timeesperaaux.size()-1) {
                gravarArq.printf("\n");
            }
        }
        
        arq.close();
    }
}

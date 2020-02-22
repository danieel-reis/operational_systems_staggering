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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CriaPDF {

    /*
     * Classe criada para manipulação de arquivos em pdf.
     */
    public void geraPdf(String filePath, String nomedoarq, double FCFS[][], int tam_FCFS, double SJFN[][], int tam_SJFN, double SJFP[][],
            int tam_SJFP, double RR[][], int tam_RR, double DO[][], int tam_DO, int quantum,  ArrayList<Double> timeretorno,
            ArrayList<Double> timeespera, ArrayList<Double> timeresposta) throws IOException, DocumentException, BadElementException,
            UnsupportedEncodingException, FileNotFoundException {
        if (filePath != null) {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath + "/" + nomedoarq + ".pdf"));
            document.open();

            document.add(new LineSeparator());
            AddCabecalho(document);
            Paragraph p = new Paragraph(" ");
            p.setLeading(5);
            document.add(p);
            document.add(new LineSeparator());
            AddLogo(document);
            String data = (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
            AddText(document, data, FCFS, tam_FCFS, SJFN, tam_SJFN, SJFP, tam_SJFP, RR, tam_RR, DO, tam_DO, quantum, timeretorno, timeespera, timeresposta);
            AddGrafico(document);

            document.close();

        }
    }

    private void AddCabecalho(Document document) throws DocumentException {
        // Adiciona o cabecalho
        Paragraph p = new Paragraph(" ");
        p.setLeading(7);
        document.add(p);
        Font f = new Font(Font.FontFamily.COURIER, 13, Font.BOLD);
        p = new Paragraph("UNIVERSIDADE FEDERAL DE OURO PRETO ", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        p = new Paragraph("Aluno: Daniel Martins Reis e Otávio de Castro", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        p = new Paragraph("Disciplina: CEA437 – Sistemas Operacionais", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        p = new Paragraph("Prof. Samuel Souza Brito", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        p = new Paragraph("Segundo Trabalho Prático", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        p = new Paragraph(" ");
        p.setLeading(7);
        document.add(p);
    }

    private void AddLogo(Document document) throws BadElementException, IOException, DocumentException {
        // Adiciona LOGO
        Image image = Image.getInstance("logoUFOP.jpg");
        image.setAbsolutePosition(475, 700);
        document.add(image);
    }

    private void AddGrafico(Document document) throws BadElementException, IOException, DocumentException {
        Image figura = Image.getInstance("grafico.png");
        figura.setAlignment(Element.ALIGN_CENTER);
        figura.scaleAbsolute(400f, 250f);
        document.add(figura);
        Paragraph p = new Paragraph(" ");
        p.setLeading(15);
        document.add(p);

        figura = Image.getInstance("grafico1.png");
        figura.setAlignment(Element.ALIGN_CENTER);
        figura.scaleAbsolute(400f, 250f);
        document.add(figura);
    }

    private void AddText(Document document, String data, double FCFS[][], int tam_FCFS, double SJFN[][], int tam_SJFN,
            double SJFP[][], int tam_SJFP, double RR[][], int tam_RR, double DO[][], int tam_DO, int quantum, 
             ArrayList<Double> timeretorno, ArrayList<Double> timeespera, ArrayList<Double> timeresposta) throws DocumentException {
        Paragraph p = new Paragraph(" ");
        p.setLeading(30);
        document.add(p);
        Font f = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
        p = new Paragraph("Algoritmos de Escalonamento", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        f = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL);
        p = new Paragraph("Data : " + data, f);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setLeading(15);
        document.add(p);
        p = new Paragraph(" ", f);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setLeading(10);
        document.add(p);

        f = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
        p = new Paragraph("FCFS", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        AddTable(document, FCFS, tam_FCFS);
        p = new Paragraph("SJFN", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        AddTable(document, SJFN, tam_SJFN);
        p = new Paragraph("SJFP", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        AddTable(document, SJFP, tam_SJFP);
        p = new Paragraph("RR - Quantum: " + quantum, f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        AddTable(document, RR, tam_RR);
        p = new Paragraph("DO", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        AddTable(document, DO, tam_DO);
        p = new Paragraph("TEMPOS MÉDIOS DE EXECUÇÃO", f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setLeading(15);
        document.add(p);
        AddTableTempos(document, timeretorno, timeespera, timeresposta);
    }

    private void AddTable(Document document, double[][] matriz, int tamanho) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1.5f, 2f, 2f, 2f, 2f, 2f});

        Font fonte = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
        PdfPCell c1 = new PdfPCell(new Phrase("Processo", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tempo de chegada", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Tempo de execução total", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Tempo de execução restante", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Início de execução", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fim de execução", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        fonte = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);

        for (int i = 0; i < tamanho; i++) {
            c1 = new PdfPCell(new Phrase(String.valueOf(matriz[i][0]), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(matriz[i][1]), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(matriz[i][2]), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(matriz[i][3]), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(matriz[i][4]), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(matriz[i][5]), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

        table.setWidthPercentage(100.0f);
        document.add(table);
    }
    
    private void AddTableTempos(Document document, ArrayList<Double> timeretorno, ArrayList<Double> timeespera, ArrayList<Double> timeresposta)
            throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1.5f, 2f, 2f, 2f});

        Font fonte = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
        PdfPCell c1 = new PdfPCell(new Phrase("Algoritmo", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tempo de retorno médio", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tempo de espera médio", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Tempo de resposta médio", fonte));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        
        ArrayList<String> names = new ArrayList<String>();
        names.add("FCFS");
        names.add("SJFN");
        names.add("SJFP");
        names.add("DO");
        names.add("RR");

        fonte = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);

        for (int i = 0; i < names.size(); i++) {
            c1 = new PdfPCell(new Phrase(names.get(i), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(timeretorno.get(i)), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(timeespera.get(i)), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(timeresposta.get(i)), fonte));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

        table.setWidthPercentage(100.0f);
        document.add(table);
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author daniel
 */
public class SelecionaCaminhoArquivo extends JPanel {

    JFileChooser fc;

    public String selectFilePath() {
        fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.setMultiSelectionEnabled(false);
        int returnVal = fc.showOpenDialog(SelecionaCaminhoArquivo.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getPath();
        }
        return null;
    }

    public String selectPaste() {
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION) {
            return arquivo.getSelectedFile().getPath();
        }
        return "";
    }
}

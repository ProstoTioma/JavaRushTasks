package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void resetDocument() {
        if (document != null)
            document.removeUndoableEditListener(view.getUndoListener());
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;

    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int choose = jFileChooser.showOpenDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try {
                FileReader fr = new FileReader(currentFile);
                new HTMLEditorKit().read(fr, document, 0);
                fr.close();
                view.resetUndo();
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }

        }
    }

    public void saveDocument() {
        if (currentFile == null) {
            saveDocumentAs();
        } else {
            view.selectHtmlTab();
            try {
                FileWriter fw = new FileWriter(currentFile);
                new HTMLEditorKit().write(fw, document, 0, document.getLength());
                fw.close();

            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }


    }


    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int choose = jFileChooser.showSaveDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter fw = new FileWriter(currentFile);
                new HTMLEditorKit().write(fw, document, 0, document.getLength());
                fw.close();

            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }

        }
    }

    public String getPlainText() {
        StringWriter sw = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(sw, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return sw.toString();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public Controller(View view) {
        this.view = view;
    }

    public void exit() {
        System.exit(0);
    }

    public void init() {
        createNewDocument();
    }
}

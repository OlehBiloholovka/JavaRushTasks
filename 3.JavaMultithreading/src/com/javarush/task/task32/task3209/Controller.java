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

    public Controller(View view) {
        this.view = view;
    }

    public void exit(){
        System.exit(0);
    }

    public void init(){
        createNewDocument();
    }

    public static void main(String[] args) {

        View viewMain = new View();
        Controller controller = new Controller(viewMain);
        viewMain.setController(controller);
        viewMain.init();
        controller.init();

    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void resetDocument(){
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
            document = (HTMLDocument) new  HTMLEditorKit().createDefaultDocument();
            document.addUndoableEditListener(view.getUndoListener());
            view.update();
        }
    }

    public void setPlainText(String text){
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;

    }

    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        fileChooser.setDialogTitle("Open File");
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            try {
                FileReader fileReader = new FileReader(currentFile);
                htmlEditorKit.read(fileReader, document, 0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument(){
        if (currentFile == null){
            saveDocumentAs();
        }else {
            view.selectHtmlTab();
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                htmlEditorKit.write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        fileChooser.setDialogTitle("Save File");
        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                htmlEditorKit.write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


}

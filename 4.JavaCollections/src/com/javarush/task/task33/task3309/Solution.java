package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {

        String resultString = null;

        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //создаем дерево, состоящее из тэгов
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlStandalone(false);

            //делаем маршалинг в объект document
            marshaller.marshal(obj, document);
            //делаем CDATA, если нужно

            changeTextToCDATA(document, document);

            //добавляем комменты
            NodeList nodeList = document.getElementsByTagName(tagName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node element = nodeList.item(i);
                element.getParentNode().insertBefore(document.createComment(comment), element);
            }
            /**
             * source - источник xml - дерева
             * result - поток, который запишет xml-дерево во writer
             */
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(writer);
            //записываем из source в result -> writer с помощью transformer
            //создаем трансформер, чтобы преобразовать дерево в результирующее дерево
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //разршаем добавление доп. пробелов, чтобы было красиво, в виде иерархии
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            resultString = writer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return resultString;
    }


    private static void changeTextToCDATA(Node start, Document doc)
    {
        if (start.getNodeType() == start.TEXT_NODE) {
            String text = start.getNodeValue();
            if (text.matches(".*[<>&'\"].*")) {
                Node cdataNode = doc.createCDATASection(text);
                start.getParentNode().replaceChild(cdataNode,start);
            }
        }
        for (Node child = start.getFirstChild(); child != null; child = child.getNextSibling()) {
            changeTextToCDATA(child,doc);
        }
    }

    public static void main(String[] args) {

    }
}

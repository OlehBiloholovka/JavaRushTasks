package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//import static com.petebevin.markdown.HTMLToken.text;

public class HtmlView implements View {
    Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".", "/") + "/" + "vacancies.html";
//    private final String filePath = "./" + this.getClass().getPackage()..getn.getName().replace(".", "/") + "/" + "vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){
        Document document;
        try {
            document = getDocument();

            Element template = document.getElementsByClass("template").first();

            Element cloneTemplate = template.clone();
            cloneTemplate.removeAttr("style");
            cloneTemplate.removeClass("template");

            document.getElementsByClass("vacancy").not("tr[class=vacancy template]").remove();

//            Elements vacancyElements = document.getElementsByClass("vacancy").not("tr[class=vacancy template]");
//            vacancyElements.forEach(Node::remove);

            vacancies.forEach(vacancy -> {
                Element clone = cloneTemplate.clone();
                clone.getElementsByClass("city").first().text(vacancy.getCity());
                clone.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                clone.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element element = clone.getElementsByTag("a").first();
                element.text(vacancy.getTitle());
                element.attr("href", vacancy.getUrl());

                template.before(clone.outerHtml());

//                Elements elementsMatchingText = document.getElementsMatchingText("<tr class=\"vacancy template\" style=\"display: none\">");
//                System.out.println(elementsMatchingText);
//                elementsMatchingText.before(element.html());
            });

//            return document.html();

        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
//            System.out.println("Some exception occurred");
        }

        return document.html();
    }

    private void updateFile(String string){

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Some exception occurred");
        }

    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}

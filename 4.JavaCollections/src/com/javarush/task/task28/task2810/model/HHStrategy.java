package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "https://hh.ua/search/vacancy?text=java+%s&page=%d"; //for valida
//    private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";
//    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java&area=%d&page=%d";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();
        try {
            int pageNumber = 0;
            while (true){

                Document document = getDocument(searchString, pageNumber);
                Elements elements = document.select("[data-qa=vacancy-serp__vacancy]");
                if (elements != null && !elements.isEmpty()){
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.select("[data-qa=vacancy-serp__vacancy-title]").text());
                        vacancy.setUrl(element.select("[data-qa=vacancy-serp__vacancy-title]").attr("href"));
                        vacancy.setCity(element.select("[data-qa=vacancy-serp__vacancy-address]").text());
                        vacancy.setCompanyName(element.select("[data-qa=vacancy-serp__vacancy-employer]").text());
                        vacancy.setSiteName("http://hh.ua");
                        String salary = element.select("[data-qa=vacancy-serp__vacancy-compensation]").text();
                        salary = salary.isEmpty() ? "" : salary;
                        vacancy.setSalary(salary);

                        list.add(vacancy);

                    }
                } else {
                    break;
                }
                pageNumber++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    protected Document getDocument(String searchString, int page) throws IOException{

        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                .referrer("http://google.com")
                .get();
    }
}

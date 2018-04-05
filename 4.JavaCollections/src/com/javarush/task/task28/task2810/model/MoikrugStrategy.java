package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();
        try {
            int pageNumber = 0;
            while (true){
                Document document = getDocument(searchString, pageNumber);
                Elements elements = document.getElementsByClass("job");
                if (elements != null && !elements.isEmpty()){
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setSiteName("https://moikrug.ru");
                        vacancy.setTitle(element.select("[class=title]").text());
                        vacancy.setUrl("https://moikrug.ru"+element.select("[class=job_icon]").attr("href"));
                        vacancy.setCity(element.select("[class=location]").text());
                        vacancy.setCompanyName(element.select("[class=company_name]").text());
                        String salary = element.select("[class=count]").text();
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

    protected Document getDocument(String searchString, int page) throws IOException {

        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                .referrer("http://google.com")
                .get();
    }
}

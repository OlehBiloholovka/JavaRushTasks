package com.javarush.task.task36.task3601;

import java.util.List;

public class Controller {
//    private View view;
//    private Model model;
//    public Controller(Model model) {
//        this.model = model;
//        this.view = new View(this);
//    }


    public Controller() {
    }

    public List<String> onDataListShow() {
        return new Model().getStringDataList();
    }

//    public void fireEventShowData() {
//        System.out.println(view.onDataListShow());
//    }
//
}

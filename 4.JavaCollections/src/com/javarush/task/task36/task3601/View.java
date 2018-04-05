package com.javarush.task.task36.task3601;

import java.util.List;

public class View {
//    private Controller controller = new Controller(new Model());

//    public View(Controller controller) {
//        this.controller = controller;
//    }

    public void fireEventShowData() {
        System.out.println(new Controller().onDataListShow());
    }

//    public List<String> onDataListShow() {
//        return new Model().getStringDataList();
//    }
//
//    public void fireEventShowData() {
//        System.out.println(view.onDataListShow());
//    }
}

package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Solution {
    public static void main(String[] args) {
//        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        LogParser logParser = new LogParser(Paths.get("/Users/olehbiloholovka/Downloads/JavaRushTasks-2/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko",null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, new Date(), null));
        System.out.println(logParser.getIPsForStatus(Status.OK,null, new Date()));
        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null, new Date()));
        System.out.println(logParser.getNumberOfUserEvents("Amigo",null, new Date()));
        System.out.println(logParser.getUsersForIP("146.34.15.5",null, new Date()));
        System.out.println(logParser.getLoggedUsers(new Date(), null));
        System.out.println(logParser.getDownloadedPluginUsers(null, new Date()));
        System.out.println(logParser.getWroteMessageUsers(null, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, null, 18));
        System.out.println(logParser.getDoneTaskUsers(null, new Date()));
        System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.LOGIN, null, null));
        System.out.println(logParser.getDatesWhenSomethingFailed(null, null));
        System.out.println(logParser.getDatesWhenErrorHappened(null, null));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getDateWhenUserSolvedTask("Eduard Petrovich Morozko", 1, null, null));
        System.out.println(logParser.getDateWhenUserDoneTask("Eduard Petrovich Morozko", 18, null, null));
        System.out.println(logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get date"));
        System.out.println(logParser.execute("get event"));
        System.out.println(logParser.execute("get status"));
        System.out.println(logParser.execute("get ip for date = \"05.01.2021 20:22:55\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get ip for date = \"05.01.2021 20:22:55\" and date between \"11.12.2000 0:00:00\" and \"11.12.3013 0:00:00\""));
        System.out.println(logParser.execute("get ip for event = \"WRITE_MESSAGE\" and date between \"11.12.100 0:00:00\" and \"11.12.3013 0:00:00\""));
        System.out.println(logParser.execute("get ip for status = \"OK\" and date between \"11.12.100 0:00:00\" and \"11.12.3013 0:00:00\""));
        System.out.println(logParser.execute("get date for event = \"SOLVE_TASk\" and date between \"30.01.2014 12:56:21\" and \"18.03.2016 23:59:59\""));
        System.out.println(logParser.execute("get ip for event = \"DOWNLOAD_PLUGIN\" and date between \"11.01.2013 0:00:00\" and \"03.11.18 23:59:59\""));
        System.out.println(logParser.execute("get ip for event = \"LOGIN\" and date between \"09.03.2047 05:04:07\" and \"29.2.31020 5:4:7\""));
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

    }
}
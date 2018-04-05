package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    private DateFormat getDateFormat(){
        return new SimpleDateFormat("d.M.y H:m:s");
    }

    private Date getDate(String dateString){

        Date date = null;
        try {
            date = getDateFormat().parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String getDateString(Date date){
        return getDateFormat().format(date);
    }

    private List<String> getLines(){
        List<String> resultList = new ArrayList<>();
        if (Files.isDirectory(logDir)){
            try {
                for (Path path : Files.newDirectoryStream(logDir)) {
                    if (path.getFileName().toString().endsWith(".log")) {
                        resultList.addAll(Files.readAllLines(path));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    private List<LinkedList<String>> getLogs(){
        List<LinkedList<String>> resultList = getLines().stream()
                .map(s -> new LinkedList<>(Arrays.asList(s.split("\t"))))
                .collect(Collectors.toList());
        resultList.forEach(strings -> {
            String[] split = strings.get(3).split(" ");
            String task = split.length == 2 ? split[1] : "";
            strings.set(3, split[0]);
            strings.add(4, task);

        });
        return resultList;
    }

    private Predicate<LinkedList<String>> getPredicate(String ip, String user, Event event, Integer task, Status status, Date after, Date before){
        return strings -> {
            boolean isIP = true;
            boolean isUser = true;
            boolean isEvent = true;
            boolean isTask = true;
            boolean isStatus = true;
            boolean isAfter = true;
            boolean isBefore = true;
            if (ip != null) {
                isIP = strings.get(QueryParameter.IP.ordinal()).equals(ip);
            }
            if (user != null) {
                isUser = strings.get(QueryParameter.USER.ordinal()).equals(user);
            }
            if (event != null) {
                isEvent = Event.valueOf(strings.get(QueryParameter.EVENT.ordinal())) == event;
            }
            if (task !=null && !strings.get(QueryParameter.TASK.ordinal()).equals("")) {
                isTask = Integer.parseInt(strings.get(QueryParameter.TASK.ordinal())) == task;
            }
            if (status != null) {
                isStatus = Status.valueOf(strings.get(QueryParameter.STATUS.ordinal())) == status;
            }
            if (after != null) {
                isAfter = !getDate(strings.get(QueryParameter.DATE.ordinal())).before(after);
            }
            if (before != null) {
                isBefore = !getDate(strings.get(QueryParameter.DATE.ordinal())).after(before);
            }
            return isIP && isUser && isEvent && isTask && isStatus && isAfter && isBefore;
        };
    }

    private Function<LinkedList<String>, String> getFunction(QueryParameter queryParameter){
        return strings -> strings.get(queryParameter.ordinal());
    }

    private List<LinkedList<String>> getListData(String ip, String user, Event event, Integer task, Status status, Date after, Date before){
        return getLogs().stream()
                .filter(getPredicate(ip, user, event, task, status, after, before))
                .collect(Collectors.toList());
    }

    private Set<String> getData(QueryParameter queryParameter, String ip, String user, Event event, Integer task, Status status, Date after, Date before){
        return getListData(ip, user, event, task, status, after, before).stream()
                .map(getFunction(queryParameter))
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getData(QueryParameter.IP, null, null, null, null, null, after, before );
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getData(QueryParameter.IP, null, user, null, null, null, after, before );
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getData(QueryParameter.IP, null, null, event, null, null, after, before );
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getData(QueryParameter.IP, null, null, null, null, status, after, before );
    }

    @Override
    public Set<String> getAllUsers() {
        return getData(QueryParameter.USER, null, null, null, null, null, null, null );
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getData(QueryParameter.USER, null, null, null, null, null, after, before ).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getData(QueryParameter.EVENT, null, user, null, null, null, after, before ).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getData(QueryParameter.USER, ip, null, null, null, null, after, before );
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getData(QueryParameter.USER, null, null, Event.LOGIN, null, null, after, before );
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getData(QueryParameter.USER, null, null, Event.DOWNLOAD_PLUGIN, null, null, after, before );
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getData(QueryParameter.USER, null, null, Event.WRITE_MESSAGE, null, null, after, before );
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getData(QueryParameter.USER, null, null, Event.SOLVE_TASK, null, null, after, before );
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getData(QueryParameter.USER, null, null, Event.SOLVE_TASK, task, null, after, before );
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getData(QueryParameter.USER, null, null, Event.DONE_TASK, null, null, after, before );
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getData(QueryParameter.USER, null, null, Event.DONE_TASK, task, null, after, before );
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getData(QueryParameter.DATE, null, user, event, null, null, after, before ).stream()
                .map(this::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getData(QueryParameter.DATE, null, null, null, null, Status.FAILED, after, before ).stream()
                .map(this::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getData(QueryParameter.DATE, null, null, null, null, Status.ERROR, after, before ).stream()
                .map(this::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return getData(QueryParameter.DATE, null, user, Event.LOGIN, null, null, after, before ).stream()
                .map(this::getDate)
                .reduce(BinaryOperator.minBy(Date::compareTo))
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return getData(QueryParameter.DATE, null, user, Event.SOLVE_TASK, task, null, after, before ).stream()
                .map(this::getDate)
                .reduce(BinaryOperator.minBy(Date::compareTo))
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return getData(QueryParameter.DATE, null, user, Event.DONE_TASK, task, null, after, before ).stream()
                .map(this::getDate)
                .reduce(BinaryOperator.minBy(Date::compareTo))
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getData(QueryParameter.DATE, null, user, Event.WRITE_MESSAGE, null, null, after, before ).stream()
                .map(this::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getData(QueryParameter.DATE, null, user, Event.DOWNLOAD_PLUGIN, null, null, after, before ).stream()
                .map(this::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getData(QueryParameter.EVENT, null, null, null, null, null, after, before ).stream()
                .map(Event::valueOf).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getData(QueryParameter.EVENT, ip, null, null, null, null, after, before ).stream()
                .map(Event::valueOf).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getData(QueryParameter.EVENT, null, user, null, null, null, after, before ).stream()
                .map(Event::valueOf).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getData(QueryParameter.EVENT, null, null, null, null, Status.FAILED, after, before ).stream()
                .map(Event::valueOf).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getData(QueryParameter.EVENT, null, null, null, null, Status.ERROR, after, before ).stream()
                .map(Event::valueOf).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return getListData(null, null, Event.SOLVE_TASK, task, null, after, before).size();

    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return getListData(null, null, Event.DONE_TASK, task, null, after, before).size();

    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        Set<String> keys = getData(QueryParameter.TASK, null, null, Event.SOLVE_TASK, null, null, after, before );
        keys.forEach(s -> resultMap.put(Integer.parseInt(s), getNumberOfAttemptToSolveTask(Integer.parseInt(s),after, before)));
        return resultMap;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        Set<String> keys = getData(QueryParameter.TASK, null, null, Event.DONE_TASK, null, null, after, before );
        keys.forEach(s -> resultMap.put(Integer.parseInt(s), getNumberOfSuccessfulAttemptToSolveTask(Integer.parseInt(s),after, before)));
        return resultMap;
    }

    private Object getNeededObject(String value, Class<?> aClass){
        if (aClass.equals(String.class)){
            return value;
        }else if (aClass.equals(Date.class)){
            return getDate(value);
        }else if (aClass.equals(Event.class)){
            return Event.valueOf(value.toUpperCase());
        }else if (aClass.equals(Status.class)){
            return Status.valueOf(value.toUpperCase());
        }else if (aClass.equals(Integer.class)){
            return Integer.parseInt(value);
        }
        return null;
    }

    private Set<Object> getQuerySet(QueryParameter getParameter, QueryParameter forParameter, String forValue, Date afterDate, Date beforeDate){
//        Predicate<LinkedList<String>> predicate = strings -> (forParameter == null || strings.get(forParameter.ordinal()).toUpperCase().equals(forValue.toUpperCase())
//                && (afterDate == null || !getDate(strings.get(QueryParameter.DATE.ordinal())).before(afterDate))
//                && (beforeDate == null || !getDate(strings.get(QueryParameter.DATE.ordinal())).after(beforeDate)));

        Predicate<LinkedList<String>> predicate = strings -> (forParameter == null || strings.get(forParameter.ordinal()).toUpperCase().equals(forValue.toUpperCase())
                && (afterDate == null || getDate(strings.get(QueryParameter.DATE.ordinal())).getTime() > afterDate.getTime())
                && (beforeDate == null || getDate(strings.get(QueryParameter.DATE.ordinal())).getTime() < beforeDate.getTime()));

//        System.out.println("QueryParameter.DATE.ordinal() - " + QueryParameter.DATE.ordinal());

//        Predicate<LinkedList<String>> predicate3or4or11 = strings -> (forParameter == null || strings.get(forParameter.ordinal()).toUpperCase().equals(forValue.toUpperCase())
//                && (afterDate == null || !getDate(strings.get(QueryParameter.DATE.ordinal())).after(afterDate))
//                && (beforeDate == null || !getDate(strings.get(QueryParameter.DATE.ordinal())).before(beforeDate)));


        Function<LinkedList<String>, Object> function = strings -> getNeededObject(strings.get(getParameter.ordinal()), getParameter.getValue());
//
//        boolean isQuery3or4or11 = forParameter != null && ((getParameter.equals(QueryParameter.IP)
//                && (forParameter.equals(QueryParameter.EVENT) || forParameter.equals(QueryParameter.STATUS)))
//                || (getParameter.equals(QueryParameter.DATE) && forParameter.equals(QueryParameter.EVENT)));

//        Predicate<LinkedList<String>> predicateMain = isQuery3or4or11 ? predicate3or4or11 : predicate;


        return getLogs().stream()
                .filter(predicate)
                .map(function)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Object> execute(String query) {

        String getValue;
        String forKey;
        String forValue;
        String after;
        String before;

        Pattern queryPattern = Pattern.compile("get\\s(?<what>[a-zA-Z]+)(\\sfor\\s(?<filter>[a-zA-Z]+)\\s\\=\\s(?<par>(\"(.*?)\")|(\")))?(\\sand date between\\s(?<after>(\"(.*?)\")|(\"))\\sand\\s(?<before>(\"(.*?)\")|(\")))?");

        Matcher matcher = queryPattern.matcher(query);
        if (matcher.find()) {
            getValue = matcher.group("what");
            forKey = matcher.group("filter");
            forValue = matcher.group("par");
            after = matcher.group("after");
            before = matcher.group("before");





            QueryParameter getParameter = QueryParameter.valueOf(getValue.toUpperCase());
            QueryParameter forParameter = forKey == null ? null : QueryParameter.valueOf(forKey.toUpperCase());
            forValue = forValue == null ? null : forValue.substring(1, forValue.length()-1);
            Date afterDate = after == null ? null : getDate(after.substring(1, after.length()-1));
            Date beforeDate = before == null ? null : getDate(before.substring(1, before.length()-1));

            return getQuerySet(getParameter, forParameter, forValue, afterDate, beforeDate);
        }
        return null;

    }

    private enum QueryParameter{
        IP(String.class),
        USER(String.class),
        DATE(Date.class),
        EVENT(Event.class),
        TASK(Integer.class),
        STATUS(Status.class);

        private Class<?> value;

        QueryParameter(Class<?> aClass) {
            this.value = aClass;
        }

        public Class<?> getValue() {
            return value;
        }
    }

    private class Query{

        private QueryParameter getParameter;
        private QueryParameter forParameter;
        private String forValue;
        private Date afterDate;
        private Date beforeDate;

        public Query(String query) {

        }

        public QueryParameter getGetParameter() {
            return getParameter;
        }

        public QueryParameter getForParameter() {
            return forParameter;
        }

        public String getForValue() {
            return forValue;
        }

        public Date getAfterDate() {
            return afterDate;
        }

        public Date getBeforeDate() {
            return beforeDate;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "getParameter=" + getParameter +
                    ", forParameter=" + forParameter +
                    ", forValue='" + forValue + '\'' +
                    ", afterDate=" + afterDate +
                    ", beforeDate=" + beforeDate +
                    '}';
        }
    }
}
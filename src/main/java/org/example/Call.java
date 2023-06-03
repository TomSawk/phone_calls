package org.example;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Call {
    private final int callerID;
    private final int callee;
    private final int callTime;
    private static  List<Call> callList = new ArrayList<Call>();

    Call(int callerID, int calleeID, int callTime){
        this.callerID=callerID;
        this.callee =calleeID;
        this.callTime=callTime;
        callList.add(this);
    }
    public int getCallerID() {
        return callerID;
    }

    public int getCalleeID() {
        return callee;
    }

    public int getCallTime() {
        return callTime;
    }
    public static List<Map.Entry<Integer, Integer>> getLongestCalls(List<Call> data, int N, Function<Call, Integer> idExtractor) {
        Map<Integer, Integer> calls = data.stream()
                .collect(Collectors.groupingBy(idExtractor, Collectors.summingInt(Call::getCallTime)));
        return calls.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(N)
                .collect(Collectors.toList());
    }
    public static List<Map.Entry<Integer, Integer>> getLargestByCallCount(List<Call> data, int N, Function<Call, Integer> idExtractor) {
        Map<Integer, Integer> counts = data.stream()
                .collect(Collectors.groupingBy(idExtractor, Collectors.summingInt(e -> 1)));
        return counts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(N)
                .collect(Collectors.toList());
    }
    public static List<Map.Entry<Integer, Integer>> getLargestCallersByUniqueCallees(List<Call> data, int N) {
        Map<Integer, Set<Integer>> callerCallees = new HashMap<>();
        for (Call call : data) {
            int caller = call.getCallerID();
            int callee = call.getCalleeID();
            callerCallees.putIfAbsent(caller, new HashSet<>());
            callerCallees.get(caller).add(callee);
        }

        Map<Integer, Integer> callerUniqueCalleesCount = callerCallees.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

        return callerUniqueCalleesCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(N)
                .collect(Collectors.toList());
    }
    public static List<Map.Entry<Integer, Integer>> getLargestCalleesByUniqueCallers(List<Call> data, int N) {
        Map<Integer, Set<Integer>> calleeCallers = new HashMap<>();
        for (Call call : data) {
            int caller = call.getCallerID();
            int callee = call.getCalleeID();
            calleeCallers.putIfAbsent(callee, new HashSet<>());
            calleeCallers.get(callee).add(caller);
        }

        Map<Integer, Integer> calleeUniqueCallersCount = calleeCallers.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

        return calleeUniqueCallersCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(N)
                .collect(Collectors.toList());
    }
    public static List<Map.Entry<Integer, Integer>> getSmallestByCallCount(List<Call> data, int N, Function<Call, Integer> idExtractor) {
        Map<Integer, Integer> callCounts = new HashMap<>();
        for (Call call : data) {
            int id = idExtractor.apply(call);
            callCounts.put(id, callCounts.getOrDefault(id, 0) + 1);
        }

        return callCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(N)
                .collect(Collectors.toList());
    }
    public static void printSmallestCalleesByCallCount(int N){
        List<Call> calls = Call.getCallList();

        List<Map.Entry<Integer, Integer>> data = getSmallestByCallCount(calls, N, Call::getCalleeID);
        System.out.println("---------------------------------------------");
        System.out.println("List of " + N + " customers who received the smallest number of calls:");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Caller with ID: " + entry.getKey() + " talked " + entry.getValue() +
                    " times");
        }
    }
    public static void printSmallestCallersByCallCount(int N){
        List<Call> calls = Call.getCallList();
        List<Map.Entry<Integer, Integer>> data = getSmallestByCallCount(calls, N, Call::getCallerID);
        System.out.println("---------------------------------------------");
        System.out.println("List of " + N + " customers who made the smallest number of calls:");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Caller with ID: " + entry.getKey() + " talked " + entry.getValue() +
                    " times");
        }
    }
    public static void printLargestCalleesByUniqueCallers(int N){
        List<Call> calls = Call.getCallList();

        List<Map.Entry<Integer, Integer>> data = getLargestCalleesByUniqueCallers(calls, N);
        System.out.println("---------------------------------------------");
        System.out.println("List of " + N +" customers who received the calls from the largest number of other customers:");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Caller with ID: " + entry.getKey() + " talked " + entry.getValue() +
                    " times");
        }
    }
    public static void printLargestCallersByUniqueCallees(int N){
        List<Call> calls = Call.getCallList();
        System.out.println("---------------------------------------------");
        List<Map.Entry<Integer, Integer>> data = getLargestCallersByUniqueCallees(calls, N);
        System.out.println("List of "+ N +" customers who called the largest number of other customers:");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Caller with ID: " + entry.getKey() + " talked " + entry.getValue() +
                    " times");
        }
    }
    public static void printLargestCallersByCallCount(int N){
        List<Call> calls = Call.getCallList();

        List<Map.Entry<Integer, Integer>> data = getLargestByCallCount(calls, N, Call::getCallerID);
        System.out.println("---------------------------------------------");
        System.out.println("List of "+ N + " customers who made the largest number of calls:");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Caller with ID: " + entry.getKey() + " made " + entry.getValue() +
                    " calls");
        }
    }
    public static void printLargestCalleesByCallCount(int N){
        List<Call> calls = Call.getCallList();
        System.out.println("---------------------------------------------");
        List<Map.Entry<Integer, Integer>> data = getLargestByCallCount(calls, N, Call::getCalleeID);
        System.out.println("List of " + N + " customers who received the largest number of calls:");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Callee with ID: " + entry.getKey() + " talked " + entry.getValue() +
                    " times");
        }
    }
    public static void printLongestCallers(int N){
        List<Call> calls = Call.getCallList();
        List<Map.Entry<Integer, Integer>> data = getLongestCalls(calls, N, Call::getCallerID);

        System.out.println("---------------------------------------------");
        System.out.println("list of " + N + " customers who talked for the longest time as callees (and this time)");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Caller with ID: " + entry.getKey() + " talked for " + entry.getValue() +
                    "seconds");
        }
    }
    public static void printLongestCallees(int N){
        List<Call> calls = Call.getCallList();
        List<Map.Entry<Integer, Integer>> data = getLongestCalls(calls, N, Call::getCalleeID);
        System.out.println("---------------------------------------------");
        System.out.println("list of " + N +  " customers who talked for the longest time as callers (and this time):");
        for (Map.Entry<Integer, Integer> entry : data) {
            System.out.println("Callee with ID: " + entry.getKey() + " talked for " + entry.getValue() +
                    "seconds");
        }
    }
    public static void printCustomerInformation(int customerId) {
        List<Call> data = getCallList();
        int callsMade = 0, callsReceived =0, totalDuration = 0;

        for (Call call : data) {
            if (call.getCallerID() == customerId) {
                callsMade++;
                totalDuration += call.getCallTime();
            }
            if (call.getCalleeID() == customerId) {
                callsReceived++;
                totalDuration += call.getCallTime();
            }
        }

        System.out.printf("\nCustomer ID: %d\nNumber of Calls Made: %d\nNumber of Calls Received: %d" +
                "\nTotal Duration of Calls: %d seconds", customerId, callsMade, callsReceived, totalDuration);
    }
    public static List<Call> getCallList(){
        return callList;
    }

    @Override
    public String toString(){
        return "Caller id: " + callerID + " Calee id: " + callee + "duration: " + callTime +"seconds";
    }
}
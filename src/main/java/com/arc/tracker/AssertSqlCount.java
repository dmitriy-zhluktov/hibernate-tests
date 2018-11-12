package com.arc.tracker;

public class AssertSqlCount {

    public static void reset() {
        QueryInfoHolder.getQueryInfo().clear();
    }

    public static void assertSelectCount(int expectedCount) {
        assertCount(expectedCount, QueryInfoHolder.getQueryInfo().getSelectCount());
    }

    private static void assertCount(int expectedCount, int actualCount) {
        if (expectedCount != actualCount) {
            throw new AssertionError("Неверное количество запросов: фактическое " + actualCount + ", ожидаемое " + expectedCount);
        }
    }
}

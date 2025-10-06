# gh-35398 reproducer

Run the `src/test/java/com/example/deadlock/TestDeadlockApplication.java` main method from your IDE.

When setting breka points, you can use the following condition to catch one source of the lock `Thread.currentThread().name.contains("reactor")`.

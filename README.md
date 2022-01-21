# Java-Event-Bus
 Fast dependency-free Event Bus for Java applications\
 **Benchmarks:** (Ran via JUnit testing)\
 `1,000,000 registration cycles took 417ms` (register/unregister)\
 `1,000,000 Invocations took 12ms` (1 Listener)\
 `1,000,000 Invocations took 38ms` (50 Listeners)
 
 **Multi Callable Listeners:**
*You are able to declare mutiple callables within one listener
```java
IEventListener eventListener = new IEventListener() {
    @EventHandler(events = SomeEvent.class, priority = Priority.MEDIUM)
    IEventCallable someEventCallable = (someEvent -> {
       // Do something on SomeEvent...
    });
    @EventHandler(events = SomeOtherEvent.class, priority = Priority.LOW)
    IEventCallable someOtherEventCallable = (someEvent -> {
       // And something on SomeOtherEvent.
    });
};
```

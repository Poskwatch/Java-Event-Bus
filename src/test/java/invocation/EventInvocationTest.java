package invocation;

import io.github.poskwatch.eventbus.api.annotations.EventHandler;
import io.github.poskwatch.eventbus.api.interfaces.IEventCallable;
import io.github.poskwatch.eventbus.api.interfaces.IEventListener;
import io.github.poskwatch.eventbus.impl.bus.EventBus;
import org.junit.jupiter.api.Test;

public class EventInvocationTest {

    private final EventBus eventBus = new EventBus();
    private final EmptyInvocationEvent event = new EmptyInvocationEvent();

    IEventListener eventListener = new IEventListener() {
        @EventHandler(events = EmptyInvocationEvent.class)
        private final IEventCallable<EmptyInvocationEvent> emptyCallable = (event->{});
    };

    @Test
    public void benchmark() {
        this.eventBus.registerListener(eventListener);
        final long preBenchmarkTimestamp = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            this.eventBus.invokeEvent(event);
        }
        this.eventBus.unregisterListener(eventListener);
        final long postBenchmarkTimestamp = System.currentTimeMillis();
        System.out.printf("1,000,000 Invocations took %dms\n", postBenchmarkTimestamp - preBenchmarkTimestamp);
    }

}

package registration;

import invocation.EmptyInvocationEvent;
import io.github.poskwatch.eventbus.api.annotations.EventHandler;
import io.github.poskwatch.eventbus.api.interfaces.IEventCallable;
import io.github.poskwatch.eventbus.api.interfaces.IEventListener;
import io.github.poskwatch.eventbus.impl.bus.EventBus;
import org.junit.jupiter.api.Test;

public class ListenerRegistrationTest {

    private final EventBus eventBus = new EventBus();

    IEventListener eventListener = new IEventListener() {
        @EventHandler(events = EmptyInvocationEvent.class)
        private final IEventCallable<EmptyInvocationEvent> emptyCallable = (event->{});
    };

    @Test
    public void benchmark() {
        final long preBenchmarkTimestamp = System.currentTimeMillis();

        for (int i = 1_000_000; i > 0; --i) {
            this.eventBus.registerListener(eventListener);
            this.eventBus.unregisterListener(eventListener);
        }

        final long postBenchmarkTimestamp = System.currentTimeMillis();
        System.out.printf("1,000,000 registration cycles took %dms\n", postBenchmarkTimestamp - preBenchmarkTimestamp);
    }

}

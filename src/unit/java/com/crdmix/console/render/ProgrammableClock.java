package com.crdmix.console.render;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class ProgrammableClock extends Clock {

    private final ZoneId zoneId;
    private long millisecondsPast;

    private final Instant base;

    public static ProgrammableClock programmableClock() {
        return new ProgrammableClock(ZoneId.systemDefault(), Instant.now(Clock.tickMillis(ZoneId.systemDefault())));
    }

    public ProgrammableClock(ZoneId zoneId, Instant base) {
        this.zoneId = zoneId;
        this.base = base;
    }

    @Override
    public ZoneId getZone() {
        return zoneId;
    }

    @Override
    public Clock withZone(ZoneId zoneId) {
        ProgrammableClock programmableClock = new ProgrammableClock(zoneId, base);
        programmableClock.setMillisecondsPast(millisecondsPast);
        return programmableClock;
    }

    @Override
    public Instant instant() {
        return base.plusMillis(millisecondsPast);
    }

    public ProgrammableClock moreMillisecondsPast(long millisecondsPast) {
        this.millisecondsPast+= millisecondsPast;
        return this;
    }

    public ProgrammableClock setMillisecondsPast(long millisecondsPast) {
        this.millisecondsPast = millisecondsPast;
        return this;
    }
}

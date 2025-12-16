package com.clipson.cezessentials.features.teleport;

import java.time.Instant;
import java.util.UUID;

public class TeleportRequest {

    private final UUID requester;
    private final Instant expiresAt;

    public TeleportRequest(UUID requester, Instant expiresAt) {
        this.requester = requester;
        this.expiresAt = expiresAt;
    }

    public UUID getRequester() {
        return requester;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }
}

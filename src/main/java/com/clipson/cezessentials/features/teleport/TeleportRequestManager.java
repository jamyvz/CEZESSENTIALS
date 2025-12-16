package com.clipson.cezessentials.features.teleport;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportRequestManager {

    private static final int TIMEOUT_SECONDS = 60;
    private final Map<UUID, TeleportRequest> requests = new HashMap<>();

    public void requestTeleport(Player requester, Player target) {
        requests.put(
                target.getUniqueId(),
                new TeleportRequest(
                        requester.getUniqueId(),
                        Instant.now().plusSeconds(TIMEOUT_SECONDS)
                )
        );
    }

    public TeleportRequest getRequest(Player target) {
        TeleportRequest request = requests.get(target.getUniqueId());
        if (request == null) return null;

        if (request.isExpired()) {
            requests.remove(target.getUniqueId());
            return null;
        }

        return request;
    }

    public void removeRequest(Player target) {
        requests.remove(target.getUniqueId());
    }

    public Player getRequester(TeleportRequest request) {
        return Bukkit.getPlayer(request.getRequester());
    }
}

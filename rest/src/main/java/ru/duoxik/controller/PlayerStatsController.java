package ru.duoxik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.duoxik.entity.PlayerStats;
import ru.duoxik.service.PlayersService;

import java.util.List;

@RestController
@RequestMapping("playerstats/")
public class PlayerStatsController {

    @Autowired
    private PlayersService playersService;

    @GetMapping("all")
    public List<PlayerStats> getAllPlayerStats() {
        return playersService.getAllPlayerStats();
    }

    @GetMapping("{nickname}")
    public PlayerStats getPlayerStatsByNickname(@PathVariable String nickname) {
        return playersService.getPlayerStats(nickname);
    }
}

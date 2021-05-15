package ru.duoxik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.duoxik.entity.PlayerInfo;
import ru.duoxik.service.playerstats.PlayersInfoService;

import java.util.List;

@RestController
@RequestMapping("api/player-info/")
public class PlayerInfoController {

    @Autowired
    private PlayersInfoService playersInfoService;

    @GetMapping("all")
    public List<PlayerInfo> getAllPlayers() {
        return playersInfoService.getAllPlayerInfos();
    }
}

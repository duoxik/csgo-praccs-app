package ru.duoxik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.duoxik.entity.PlayerStats;
import ru.duoxik.service.dao.PlayerStatsDao;

import java.util.List;

@Service
public class PlayersServiceImpl implements PlayersService {

    @Autowired
    private PlayerStatsDao playerStatsDao;

    @Override
    public PlayerStats getPlayerStats(String nickname) {
        return playerStatsDao.getPlayerStatsByNickname(nickname);
    }

    @Override
    public List<PlayerStats> getAllPlayerStats() {
        return playerStatsDao.getAllPlayerStats();
    }
}

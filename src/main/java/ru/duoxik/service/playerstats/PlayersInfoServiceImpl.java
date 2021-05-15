package ru.duoxik.service.playerstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.duoxik.entity.PlayerInfo;
import ru.duoxik.service.playerstats.dao.PlayersInfoDao;

import java.util.List;

@Service
public class PlayersInfoServiceImpl implements PlayersInfoService {

    @Autowired
    private PlayersInfoDao playersInfoDao;

    @Override
    public PlayerInfo getPlayerInfo(Integer fastcupUserId) {
        return playersInfoDao.getPlayerInfoByFastcupUserId(fastcupUserId);
    }

    @Override
    public List<PlayerInfo> getAllPlayerInfos() {
        return playersInfoDao.getAllPlayersInfo();
    }

    @Override
    public PlayerInfo createPlayer(String nickname, Integer fastcupUserId) {
        return playersInfoDao.createPlayer(nickname, fastcupUserId);
    }

    @Override
    public boolean updatePlayer(PlayerInfo playerInfo) {
        return playersInfoDao.updatePlayer(playerInfo) > 0;
    }
}

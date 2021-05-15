package ru.duoxik.service.uploadmatch;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.duoxik.entity.Match;
import ru.duoxik.entity.Platform;
import ru.duoxik.entity.PlayerInfo;
import ru.duoxik.entity.Team;
import ru.duoxik.service.matchparser.PlatformService;
import ru.duoxik.service.matchparser.factory.PlatformServiceFactory;
import ru.duoxik.service.playerstats.PlayersInfoService;
import ru.duoxik.service.uploadmatch.dao.MatchDao;
import ru.duoxik.utils.EloUtils;

import java.util.Optional;

@Service
public class UploadMatchServiceImpl implements UploadMatchService {

    private final PlatformServiceFactory platformServiceFactory;
    private final PlayersInfoService playersInfoService;
    private final MatchDao matchDao;

    @Autowired
    public UploadMatchServiceImpl(PlatformServiceFactory platformServiceFactory,
                                  PlayersInfoService playersInfoService,
                                  MatchDao matchDao) {
        this.platformServiceFactory = platformServiceFactory;
        this.playersInfoService = playersInfoService;
        this.matchDao = matchDao;
    }

    @Override
    public Match uploadMatch(Platform platform, Integer matchId) {
        PlatformService parserService = platformServiceFactory.getService(platform);

        if (matchDao.getMatch(matchId) != null) {
            throw new RuntimeException("Match already uploaded");
        }

        try {
            Match match = parserService.getMatch(matchId);
            Team team1 = match.getTeam1();
            Team team2 = match.getTeam2();
            int avgRankTeam1 = averageTeamRank(team1);
            int avgRankTeam2 = averageTeamRank(team2);
            updatePlayersInfoByTeam(team1, avgRankTeam1, avgRankTeam2);
            updatePlayersInfoByTeam(team2, avgRankTeam2, avgRankTeam1);
            matchDao.saveMatch(matchId);
            return match;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updatePlayersInfoByTeam(Team yourTeam, Integer yourTeamAvgRank, Integer otherTeamAvgRank) {
        yourTeam.getPlayers().forEach(player -> {
            boolean yourTeamWinner = yourTeam.getWinner();
            PlayerInfo playerInfo = getPlayerInfo(player.getFastcupUserId());
            Integer newRank = yourTeamWinner
                    ? playerInfo.getRank() + EloUtils.calculateDeltaWin(yourTeamAvgRank, otherTeamAvgRank)
                    : playerInfo.getRank() - EloUtils.calculateDeltaLose(yourTeamAvgRank, otherTeamAvgRank);
            Integer newKills = playerInfo.getKills() + player.getKills();
            Integer newDeaths = playerInfo.getDeaths() + player.getDeaths();
            Integer newTotalMatches = playerInfo.getTotalMatches() + 1;
            Integer newWonMatches = yourTeamWinner ? playerInfo.getWonMatches() + 1 : playerInfo.getWonMatches();

            playersInfoService.updatePlayer(
                    PlayerInfo.builder()
                            .nickname(player.getNickname())
                            .id(playerInfo.getId())
                            .deaths(newDeaths)
                            .kills(newKills)
                            .rank(newRank)
                            .totalMatches(newTotalMatches)
                            .wonMatches(newWonMatches)
                            .build()
            );
        });
    }

    /**
     * Рассчитывает средний ранк по команде
     *
     * @param team команда
     * @return средний ранк
     */
    private int averageTeamRank(Team team) {
        return (int) Math.round(team.getPlayers().stream()
                .map(player -> getPlayerInfo(player.getFastcupUserId()).getRank())
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage());
    }

    private PlayerInfo getPlayerInfo(Integer fastcupUserId) {
        return Optional.ofNullable(playersInfoService.getPlayerInfo(fastcupUserId))
                .orElseGet(() -> playersInfoService.createPlayer(RandomStringUtils.randomAlphabetic(10), fastcupUserId));
    }
}

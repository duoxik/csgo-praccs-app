package ru.duoxik.service.shuffle;

import com.google.common.collect.Sets;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.duoxik.entity.PlayerInfo;
import ru.duoxik.entity.ShuffleVariation;
import ru.duoxik.service.playerstats.PlayersInfoService;
import ru.duoxik.utils.EloUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ShuffleServiceImpl implements ShuffleService {

    private static final int DEFAULT_PLAYERS_IN_TEAM_COUNT = 5;
    private static final int DEFAULT_PLAYERS_IN_MATCH_COUNT = 10;

    private final PlayersInfoService playersInfoService;

    @Autowired
    public ShuffleServiceImpl(PlayersInfoService playersInfoService) {
        this.playersInfoService = playersInfoService;
    }

    @Override
    public ShuffleVariation shuffle(@NonNull List<Integer> fastcupUserIds) {
        if (fastcupUserIds.size() != DEFAULT_PLAYERS_IN_MATCH_COUNT) {
            throw new IllegalArgumentException("Count of players could be " + DEFAULT_PLAYERS_IN_MATCH_COUNT);
        }

        List<PlayerInfo> players = fastcupUserIds.stream().map(id ->
                Optional.ofNullable(playersInfoService.getPlayerInfo(id))
                        .orElseThrow(() -> new IllegalArgumentException("Not found player with id = " + id))
        ).collect(Collectors.toList());

        Set<Set<Integer>> combinations = Sets.combinations(new HashSet<>(fastcupUserIds), DEFAULT_PLAYERS_IN_TEAM_COUNT);

        Map<ShuffleVariation, Integer> weightMap = combinations.stream()
                .map(combination -> {
                    List<PlayerInfo> leftTeam = new ArrayList<>();
                    List<PlayerInfo> rightTeam = new ArrayList<>();

                    players.forEach(player -> {
                        if (combination.contains(player.getId())) {
                            leftTeam.add(player);
                        } else {
                            rightTeam.add(player);
                        }
                    });

                    return ShuffleVariation.builder()
                            .leftTeam(leftTeam)
                            .leftTeamAvgRank(EloUtils.calculateTeamAvgRank(leftTeam))
                            .rightTeam(rightTeam)
                            .rightTeamAvgRank(EloUtils.calculateTeamAvgRank(rightTeam))
                            .build();
                }).collect(Collectors.toMap(
                        shuffleVariation -> shuffleVariation,
                        shuffleVariation -> {
                            int abs = Math.abs(shuffleVariation.getLeftTeamAvgRank()
                                    - shuffleVariation.getRightTeamAvgRank());
                            return abs != 0 ? abs : abs + 1;
                        }
                ));

        int weightSum = weightMap.values().stream().mapToInt(Integer::intValue).sum();

        // rebalancing
        weightMap.entrySet().stream().forEach(entry -> {
            weightMap.put(entry.getKey(), weightSum - entry.getValue());
        });

        int rebalancedWeightSum = weightMap.values().stream().mapToInt(Integer::intValue).sum();

        TreeMap<Double, ShuffleVariation> probabilityMap = new TreeMap<>();
        double total = 0.0d;
        for (Map.Entry<ShuffleVariation, Integer> entry : weightMap.entrySet()) {
            double probability = (double) entry.getValue() / rebalancedWeightSum;
            probabilityMap.put(total += probability, entry.getKey());
        }

        Random generator = new Random();
        double value = generator.nextDouble();
        ShuffleVariation result = probabilityMap.ceilingEntry(value).getValue();

        return result;
    }
}

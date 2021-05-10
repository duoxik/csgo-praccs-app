package ru.duoxik.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.duoxik.entity.PlayerStats;

import java.util.List;

@Component
public class PlayerStatsDao {

    private static final String GET_PLAYER_STATS_BY_NICKNAME =
            "select p.nickname, ps.kills, ps.deaths, ps.total_matches, ps.won_matches, ps.rank from players p " +
                    "inner join players_stats ps on p.id = ps.player_id where p.nickname = ?";

    private static final String GET_ALL_PLAYER_STATS_SQL = "select p.nickname, ps.kills, ps.deaths, ps.total_matches, " +
            "ps.won_matches, ps.rank from players p inner join players_stats ps on p.id = ps.player_id";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PlayerStats getPlayerStatsByNickname(String nickname) {
        return jdbcTemplate.queryForObject(GET_PLAYER_STATS_BY_NICKNAME, new Object[] { nickname }, MAPPER);
    }

    public List<PlayerStats> getAllPlayerStats() {
        return jdbcTemplate.query(GET_ALL_PLAYER_STATS_SQL, MAPPER);
    }

    private static final RowMapper<PlayerStats> MAPPER = (rs, rowNum) -> PlayerStats.builder()
            .nickname(rs.getString("nickname"))
            .kills(rs.getInt("kills"))
            .deaths(rs.getInt("deaths"))
            .rank(rs.getInt("rank"))
            .totalMatches(rs.getInt("total_matches"))
            .wonMatches(rs.getInt("won_matches"))
            .build();
}

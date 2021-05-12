package ru.duoxik.service.playerstats.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.duoxik.entity.PlayerInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayersInfoDao {

    private static final String GET_PLAYER_INFO_BY_NICKNAME =
            "select p.fastcup_user_id, p.nickname, pi.kills, pi.deaths, pi.total_matches, pi.won_matches, pi.rank from players p " +
                    "inner join players_info pi on p.id = pi.player_id where p.nickname = ?";

    private static final String GET_ALL_PLAYERS_INFO_SQL = "select p.fastcup_user_id, p.nickname, pi.kills, pi.deaths, pi.total_matches, " +
            "pi.won_matches, pi.rank from players p inner join players_info pi on p.id = pi.player_id";

    private static final String GET_PLAYER_INFO_BY_FASTCUP_USER_ID =
            "select p.fastcup_user_id, p.nickname, pi.kills, pi.deaths, pi.total_matches, pi.won_matches, pi.rank from players p " +
                    "inner join players_info pi on p.id = pi.player_id where p.fastcup_user_id = ?";

    private static final String UPDATE_PLAYER_INFO_SQL =
            "update players_info set rank = ?, kills = ?, deaths = ?, total_matches = ?, won_matches = ? " +
                    "where player_id = (select id from players where nickname = ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PlayerInfo getPlayerInfoByNickname(String nickname) {
        try {
            return jdbcTemplate.queryForObject(GET_PLAYER_INFO_BY_NICKNAME, new Object[]{nickname}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PlayerInfo> getAllPlayersInfo() {
        return jdbcTemplate.query(GET_ALL_PLAYERS_INFO_SQL, MAPPER);
    }

    public PlayerInfo getPlayerInfoByFastcupUserId(Integer fastcupUserId) {
        try {
            return jdbcTemplate.queryForObject(GET_PLAYER_INFO_BY_FASTCUP_USER_ID, new Object[]{fastcupUserId}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public PlayerInfo createPlayer(String nickname, Integer fastcupUserId) {
        SimpleJdbcInsert insertPlayer = new SimpleJdbcInsert(jdbcTemplate);
        insertPlayer.withTableName("players").usingGeneratedKeyColumns("id");
        Map<String, Object> playerParams = new HashMap<>();
        playerParams.put("nickname", nickname);
        playerParams.put("fastcup_user_id", fastcupUserId);
        Integer id = insertPlayer.executeAndReturnKey(new MapSqlParameterSource(playerParams)).intValue();

        SimpleJdbcInsert insertPlayerInfo = new SimpleJdbcInsert(jdbcTemplate);
        insertPlayerInfo.withTableName("players_info").usingColumns("player_id");
        Map<String, Object> playerInfoParams = new HashMap<>();
        playerInfoParams.put("player_id", id);
        insertPlayerInfo.execute(new MapSqlParameterSource(playerInfoParams));

        return getPlayerInfoByNickname(nickname);
    }

    public int updatePlayer(PlayerInfo info) {
        return jdbcTemplate.update(UPDATE_PLAYER_INFO_SQL, info.getRank(), info.getKills(),
                info.getDeaths(), info.getTotalMatches(), info.getWonMatches(), info.getNickname());
    }

    private static final RowMapper<PlayerInfo> MAPPER = (rs, rowNum) -> PlayerInfo.builder()
            .id(rs.getInt("fastcup_user_id"))
            .nickname(rs.getString("nickname"))
            .kills(rs.getInt("kills"))
            .deaths(rs.getInt("deaths"))
            .rank(rs.getInt("rank"))
            .totalMatches(rs.getInt("total_matches"))
            .wonMatches(rs.getInt("won_matches"))
            .build();
}

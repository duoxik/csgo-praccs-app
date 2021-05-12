package ru.duoxik.service.uploadmatch.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MatchDao {

    public static final String GET_MATCH_SQL = "select id from matches where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer getMatch(Integer matchId) {
        try {
            return jdbcTemplate.queryForObject(GET_MATCH_SQL, new Object[]{matchId}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveMatch(Integer matchId) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("matches").usingColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("id", matchId);
        insert.execute(new MapSqlParameterSource(params));
    }
}
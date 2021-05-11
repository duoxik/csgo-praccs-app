package ru.duoxik.service.matchparser;

import lombok.NonNull;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.duoxik.entity.Match;
import ru.duoxik.entity.PlayerStats;
import ru.duoxik.entity.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("fastcupService")
public class FastcupServiceImpl implements PlatformService {

    private static final String MATCH_URL_REQUEST = "https://hasura.fastcup.net/v1/graphql";

    @Override
    public Match getMatch(@NonNull Integer matchId) throws IOException {
        HttpPost post = new HttpPost(MATCH_URL_REQUEST);
        String requestJson = String.format("{\"operationName\":\"GetMatch\",\"variables\":{\"id\":%d,\"gameID\":1},\"query\":\"fragment match_member on match_members {\\n  match_id\\n  hash\\n  ready\\n  match_team_id\\n  rating_diff\\n  connected\\n  is_leaver\\n  kills\\n  deaths\\n  assists\\n  private {\\n    party_id\\n    rating\\n    user {\\n      id\\n      nickName: nick_name\\n      avatar\\n      online\\n      isMobile: is_mobile\\n      link\\n      stats(\\n        where: {game_id: {_eq: $gameID}, map_id: {_is_null: true}, game_mode_id: {_is_null: false}}\\n      ) {\\n        gameModeID: game_mode_id\\n        rating\\n        __typename\\n      }\\n      city {\\n        id\\n        regionID: region_id\\n        name_ru\\n        name_uk\\n        name_en\\n        name_de\\n        name_pl\\n        name_pt\\n        name_es\\n        name_hbs\\n        name_tr\\n        __typename\\n      }\\n      country {\\n        id\\n        name_ru\\n        name_uk\\n        name_en\\n        name_de\\n        name_pl\\n        name_pt\\n        name_es\\n        name_hbs\\n        name_tr\\n        iso2\\n        __typename\\n      }\\n      __typename\\n    }\\n    __typename\\n  }\\n  __typename\\n}\\n\\nquery GetMatch($id: Int!, $gameID: smallint!) {\\n  match: matches_by_pk(id: $id) {\\n    id\\n    status\\n    game_status\\n    has_winner\\n    created_at\\n    started_at\\n    finished_at\\n    scheduled_at\\n    readiness_passed\\n    last_update\\n    server_instance_id\\n    teamspeak_server_id\\n    tv_address_hidden\\n    fake_server_region_id\\n    is_paused\\n    creator_id\\n    dev_build\\n    anticheat_enabled\\n    type\\n    best_of\\n    cancellation_reason\\n    password\\n    chat_id\\n    server_region_id\\n    game_id\\n    game_mode_id\\n    max_rounds_count\\n    map_banpick_config_id\\n    referee_check_requested\\n    result_confirmed\\n    maps(order_by: {number: asc}) {\\n      id\\n      number\\n      game_status\\n      started_at\\n      finished_at\\n      demo_url\\n      demo_deleted\\n      map_id\\n      __typename\\n    }\\n    teams(order_by: {id: asc}) {\\n      id\\n      captain_id\\n      name\\n      score\\n      size\\n      chat_id\\n      is_winner\\n      initial_side\\n      team {\\n        id\\n        name\\n        logo\\n        tag\\n        __typename\\n      }\\n      mapStats {\\n        match_team_id\\n        match_map_id\\n        score\\n        is_winner\\n        initial_side\\n        __typename\\n      }\\n      __typename\\n    }\\n    serverInstance {\\n      id\\n      ip\\n      port\\n      tv_port\\n      __typename\\n    }\\n    members {\\n      ...match_member\\n      __typename\\n    }\\n    tournament {\\n      id\\n      name\\n      __typename\\n    }\\n    tournamentStage {\\n      id\\n      name\\n      outgoings {\\n        id\\n        number\\n        matchLink {\\n          match_id\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    tournamentRound {\\n      id\\n      name\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}", matchId);
        post.setEntity(new StringEntity(requestJson));

        try (CloseableHttpResponse response = HttpClients.createDefault().execute(post)) {
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Request status code is not 200");
            }

            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject matchObj = obj.getJSONObject("data").getJSONObject("match");

            JSONArray playersArr = matchObj.getJSONArray("members");
            List<PlayerStats> players = new ArrayList<>();
            for (int i = 0; i < playersArr.length(); i++) {
                JSONObject playerObj = (JSONObject) playersArr.get(i);
                JSONObject playerInfoObj = playerObj.getJSONObject("private").getJSONObject("user");

                players.add(PlayerStats.builder()
                        .kills(playerObj.getInt("kills"))
                        .deaths(playerObj.getInt("deaths"))
                        .fastcupUserId(playerInfoObj.getInt("id"))
                        .teamId(playerObj.getInt("match_team_id"))
                        .nickname(playerInfoObj.getString("nickName"))
                        .build());
            }

            JSONArray teamsArr = matchObj.getJSONArray("teams");

            return Match.builder()
                    .team1(buildTeam((JSONObject) teamsArr.get(0), players))
                    .team2(buildTeam((JSONObject) teamsArr.get(1), players))
                    .build();
        }
    }

    private static Team buildTeam(JSONObject teamObj, List<PlayerStats> players) {
        Integer teamId = teamObj.getInt("id");
        return Team.builder()
                .id(teamId)
                .winner(teamObj.getBoolean("is_winner"))
                .score(teamObj.getInt("score"))
                .players(players.stream()
                        .filter(player -> player.getTeamId().equals(teamId))
                        .collect(Collectors.toList()))
                .build();
    }
}

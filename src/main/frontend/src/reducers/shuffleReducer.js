import {
  SHUFFLE_PLAYERS_ERROR,
  SHUFFLE_PLAYERS_REQUEST,
  SHUFFLE_PLAYERS_SUCCESS,
} from "../actions/shuffleActions";

const initialStore = {
  leftTeam: [],
  leftTeamAvgRank: 0,
  rightTeam: [],
  rightTeamAvgRank: 0,
  isShuffleInProgress: false,
  isShuffleContainsError: false,
};

export default function shuffleReducer(store = initialStore, action) {
  switch (action.type) {
    case SHUFFLE_PLAYERS_REQUEST: {
      return {
        ...store,
        isShuffleInProgress: true,
        isShuffleContainsError: false,
      };
    }
    case SHUFFLE_PLAYERS_SUCCESS: {
      const {
        leftTeam,
        leftTeamAvgRank,
        rightTeam,
        rightTeamAvgRank,
      } = action.payload;

      return {
        ...store,
        leftTeam: [...leftTeam],
        leftTeamAvgRank: leftTeamAvgRank,
        rightTeam: [...rightTeam],
        rightTeamAvgRank: rightTeamAvgRank,
        isShuffleInProgress: false,
        isShuffleContainsError: false,
      };
    }
    case SHUFFLE_PLAYERS_ERROR: {
      return {
        ...store,
        isShuffleInProgress: false,
        isShuffleContainsError: true,
      };
    }
  }
  return store;
}

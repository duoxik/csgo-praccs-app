import {
  LOAD_ALL_STATS_SUCCESS,
  UPLOAD_MATCH_REQUEST,
  UPLOAD_MATCH_SUCCESS,
} from "../actions/playerStatsActions";

const initialStore = {
  stats: [],
  statsNeedUpdate: true,
};

export default function playerStatsReducer(store = initialStore, action) {
  switch (action.type) {
    case LOAD_ALL_STATS_SUCCESS: {
      return {
        ...store,
        stats: [...action.payload],
        statsNeedUpdate: false,
      };
    }
    case UPLOAD_MATCH_REQUEST: {
      return {
        ...store,
      };
    }
    case UPLOAD_MATCH_SUCCESS: {
      return {
        ...store,
        statsNeedUpdate: true,
      };
    }
  }
  return store;
}

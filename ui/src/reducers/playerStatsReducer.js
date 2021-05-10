import { LOAD_ALL_STATS_SUCCESS } from "../actions/playerStatsActions";

const initialStore = {
  stats: [],
};

export default function playerStatsReducer(store = initialStore, action) {
  switch (action.type) {
    case LOAD_ALL_STATS_SUCCESS: {
      return {
        ...store,
        stats: [...action.payload],
      };
    }
  }
  return store;
}

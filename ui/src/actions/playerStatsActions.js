import { createAction } from "redux-api-middleware";

export const LOAD_ALL_STATS_REQUEST = "@@stats/LOAD_STATS_REQUEST";
export const LOAD_ALL_STATS_SUCCESS = "@@stats/LOAD_STATS_SUCCESS";
export const LOAD_ALL_STATS_ERROR = "@@stats/LOAD_STATS_ERROR";

export const loadAllStats = () => {
  return createAction({
    endpoint: "/api/player-info/all",
    method: "GET",
    types: [
      LOAD_ALL_STATS_REQUEST,
      LOAD_ALL_STATS_SUCCESS,
      LOAD_ALL_STATS_ERROR,
    ],
  });
};

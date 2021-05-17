import { createAction } from "redux-api-middleware";

export const SHUFFLE_PLAYERS_REQUEST = "@@shuffle/SHUFFLE_PLAYERS_REQUEST";
export const SHUFFLE_PLAYERS_SUCCESS = "@@shuffle/SHUFFLE_PLAYERS_SUCCESS";
export const SHUFFLE_PLAYERS_ERROR = "@@shuffle/SHUFFLE_PLAYERS_ERROR";

export const shufflePlayers = (fastcupUserIds) => {
  return createAction({
    endpoint: "/api/shuffle",
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: `{ "fastcupUserIds": [${fastcupUserIds}] }`,
    types: [
      SHUFFLE_PLAYERS_REQUEST,
      SHUFFLE_PLAYERS_SUCCESS,
      SHUFFLE_PLAYERS_ERROR,
    ],
  });
};

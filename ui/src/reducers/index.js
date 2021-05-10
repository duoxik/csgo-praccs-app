import { combineReducers } from "redux";
import playerStatsReducer from "./playerStatsReducer";
import { connectRouter } from "connected-react-router";

export default (history) =>
  combineReducers({
    router: connectRouter(history),
    playerStatsReducer: playerStatsReducer,
  });

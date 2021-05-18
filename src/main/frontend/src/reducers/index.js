import { combineReducers } from "redux";
import playerStatsReducer from "./playerStatsReducer";
import { connectRouter } from "connected-react-router";
import dialogReducer from "./dialogReducer";
import shuffleReducer from "./shuffleReducer";

export default (history) =>
  combineReducers({
    router: connectRouter(history),
    playerStatsReducer: playerStatsReducer,
    dialogReducer: dialogReducer,
    shuffleReducer: shuffleReducer,
  });

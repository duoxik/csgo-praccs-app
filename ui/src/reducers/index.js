import { combineReducers } from "redux";
import chatReducer from "./chatReducer";
import profileReducer from "./profileReducer";
import headerReducer from "./headerReducer";
import { connectRouter } from "connected-react-router";

export default (history) =>
  combineReducers({
    router: connectRouter(history),
    chatReducer,
    profileReducer,
    headerReducer,
  });

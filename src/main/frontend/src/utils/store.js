import { applyMiddleware, createStore } from "redux";
import initReducers from "./../reducers";
import middlewares from "../middlewares";
import { createBrowserHistory } from "history";
import { routerMiddleware } from "connected-react-router";
import storage from "redux-persist/lib/storage";
import autoMergeLevel2 from "redux-persist/lib/stateReconciler/autoMergeLevel2";
import { persistStore, persistReducer } from "redux-persist";
import { composeWithDevTools } from "redux-devtools-extension";

const persistConfig = {
  key: "csgo_praccs",
  storage,
  stateReconciler: autoMergeLevel2,
  whitelist: [
    //"chatReducer"
  ],
};

export const history = createBrowserHistory();

function initStore() {
  const initialStore = {};

  const store = createStore(
    persistReducer(persistConfig, initReducers(history)),
    initialStore,
    composeWithDevTools(
      applyMiddleware(routerMiddleware(history), ...middlewares)
    )
  );

  const persistor = persistStore(store);
  return { store, persistor };
}

export default initStore;

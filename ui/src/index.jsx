import React from "react";
import ReactDom from "react-dom";
import { ConnectedRouter } from "connected-react-router";
import { Provider } from "react-redux";
import initStore, { history } from "./utils/store";
import Router from "./components/Router";
import { PersistGate } from "redux-persist/integration/react";

const { store, persistor } = initStore();

ReactDom.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <ConnectedRouter history={history}>
        <Router />
      </ConnectedRouter>
    </PersistGate>
  </Provider>,
  document.getElementById("app")
);

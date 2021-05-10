import React from "react";
import { Switch, Route } from "react-router-dom";
import Layout from "../containers/Layout";

export default class Router extends React.Component {
  render() {
    return (
      <Switch>
        <Route exact path="/" component={Layout}></Route>
        <Route
          exact
          path="/chat/:id"
          render={(props) => (
            <Layout display="chat" chatId={props.match.params.id} />
          )}
        />
        <Route
          exact
          path="/profile"
          render={() => <Layout display="profile" />}
        />
      </Switch>
    );
  }
}

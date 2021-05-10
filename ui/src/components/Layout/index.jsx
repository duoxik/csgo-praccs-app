import React from "react";
import PlayerStats from "../../containers/PlayerStats";
import Paper from "@material-ui/core/Paper";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import { Container } from "@material-ui/core";
import "./style.css";

export default class Layout extends React.Component {
  static propTypes = {};

  render() {
    return (
      <div className="layout">
        <Paper>
          <Tabs
            // value={value}
            // onChange={handleChange}
            indicatorColor="primary"
            textColor="primary"
            centered
          >
            <Tab label="Players" />
            <Tab label="Shuffle" />
            <Tab label="Upload match" />
          </Tabs>
        </Paper>
        <div className="content-wrapper">
          <div className="content">
            <PlayerStats />
          </div>
        </div>
      </div>
    );
  }
}

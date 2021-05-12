import React from "react";
import PlayerStats from "../../containers/PlayerStats";
import UploadMatchDialog from "../../containers/UploadMatchDialog";
import Paper from "@material-ui/core/Paper";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import "./style.css";

export default class Layout extends React.Component {
  state = {
    openUploadDialog: false,
  };

  handleOpenUploadDialog = () => {
    this.setState({
      openUploadDialog: true,
    });
  };

  handleCloseUploadDialog = () => {
    this.setState({
      openUploadDialog: false,
    });
  };

  render() {
    return (
      <div className="layout">
        <Paper>
          <Tabs indicatorColor="primary" textColor="primary" centered>
            <Tab label="Players" />
            <Tab label="Shuffle" />
            <Tab label="Upload match" onClick={this.handleOpenUploadDialog} />
          </Tabs>
        </Paper>
        <div className="content-wrapper">
          <div className="content">
            <PlayerStats />
            <UploadMatchDialog
              open={this.state.openUploadDialog}
              handleClose={this.handleCloseUploadDialog}
            />
          </div>
        </div>
      </div>
    );
  }
}

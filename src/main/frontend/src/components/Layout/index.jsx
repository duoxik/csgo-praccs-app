import React from "react";
import PlayerStats from "../../containers/PlayerStats";
import UploadMatchDialog from "../../containers/UploadMatchDialog";
import ShuffleDialog from "../../containers/ShuffleDialog";
import ShuffleResultDialog from "../../containers/ShuffleResultDialog";
import Paper from "@material-ui/core/Paper";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import "./style.css";
import PropTypes from "prop-types";
import { DIALOGS } from "../../reducers/dialogReducer";

export default class Layout extends React.Component {
  static propTypes = {
    openDialog: PropTypes.func.isRequired,
  };

  render() {
    const { openDialog } = this.props;

    return (
      <div className="layout">
        <Paper>
          <Tabs indicatorColor="primary" textColor="primary" centered>
            <Tab label="Players" />
            <Tab
              label="Shuffle"
              onClick={() => {
                openDialog(DIALOGS.SHUFFLE);
              }}
            />
            <Tab
              label="Upload match"
              onClick={() => {
                openDialog(DIALOGS.UPLOAD);
              }}
            />
          </Tabs>
        </Paper>
        <div className="content-wrapper">
          <div className="content">
            <PlayerStats />
            <UploadMatchDialog />
            <ShuffleDialog />
            <ShuffleResultDialog />
          </div>
        </div>
      </div>
    );
  }
}

import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import PropTypes from "prop-types";
import { DIALOGS } from "../../reducers/dialogReducer";

const columns = [
  { field: "nickname", headerName: "Nickname", width: 220 },
  {
    field: "rank",
    headerName: "Rank",
    type: "number",
    width: 140,
    cellClassName: "rank",
  },
  { field: "kills", headerName: "Kills", type: "number", width: 140 },
  {
    field: "deaths",
    headerName: "Deaths",
    type: "number",
    width: 140,
  },
  {
    field: "totalMatches",
    headerName: "Total matches",
    type: "number",
    width: 180,
  },
  {
    field: "wonMatches",
    headerName: "Won matches",
    type: "number",
    width: 180,
  },
];

export default class UploadMatchDialog extends React.Component {
  static propTypes = {
    isDialogOpen: PropTypes.bool.isRequired,
    closeDialog: PropTypes.func.isRequired,
  };

  state = {
    matchId: "",
  };

  handleMatchIdChange = (event) => {
    this.setState({
      matchId: event.target.value,
    });
  };

  handleCloseDialog = () => {
    this.props.closeDialog(DIALOGS.UPLOAD);
  };

  handleUploadClick = () => {
    this.props.uploadMatch(this.state.matchId);
    this.handleCloseDialog();
    this.setState({
      matchId: "",
    });
  };

  render() {
    const { isDialogOpen } = this.props;
    return (
      <div>
        <Dialog open={isDialogOpen} onClose={this.handleCloseDialog}>
          <DialogTitle>Uploader</DialogTitle>
          <DialogContent>
            <DialogContentText>
              To upload match, please enter match number.
            </DialogContentText>
            <TextField
              autoFocus
              margin="dense"
              id="name"
              label="Match id"
              type="number"
              value={this.state.matchId}
              onChange={this.handleMatchIdChange}
              fullWidth
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleCloseDialog} color="primary">
              Cancel
            </Button>
            <Button onClick={this.handleUploadClick} color="primary">
              Upload
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import PropTypes from "prop-types";

export default class UploadMatchDialog extends React.Component {
  static propTypes = {
    open: PropTypes.bool.isRequired,
    handleClose: PropTypes.func.isRequired,
    uploadMatch: PropTypes.func.isRequired,
  };

  state = {
    matchId: 5297220,
  };

  handleMatchIdChange = (event) => {
    this.setState({
      matchId: event.target.value,
    });
  };

  handleUploadClick = () => {
    this.props.uploadMatch(this.state.matchId);
    this.props.handleClose();
    this.setState({
      matchId: "",
    });
  };

  render() {
    const { open, handleClose, uploadMatch } = this.props;
    return (
      <div>
        <Dialog
          open={open}
          onClose={handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Uploader</DialogTitle>
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
            <Button onClick={handleClose} color="primary">
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

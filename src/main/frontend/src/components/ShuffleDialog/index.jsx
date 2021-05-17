import React from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import PropTypes from "prop-types";
import { DataGrid } from "@material-ui/data-grid";
import "./style.css";

const columns = [
  { field: "nickname", headerName: "Nickname", width: 220 },
  {
    field: "rank",
    headerName: "Rank",
    type: "number",
    width: 140,
    cellClassName: "rank",
  },
];

export default class ShuffleDialog extends React.Component {
  static propTypes = {
    open: PropTypes.bool.isRequired,
    handleClose: PropTypes.func.isRequired,
    stats: PropTypes.arrayOf(
      PropTypes.shape({
        fastcupId: PropTypes.number.isRequired,
        nickname: PropTypes.string.isRequired,
        rank: PropTypes.number.isRequired,
      })
    ),
    shufflePlayers: PropTypes.func.isRequired,
  };

  state = {
    fastcupUserIds: "",
  };

  handleSelectionModelChange = (newSelection) => {
    this.setState({
      fastcupUserIds: newSelection.selectionModel,
    });
  };

  handleShuffleClick = () => {
    const { shufflePlayers, handleClose } = this.props;
    shufflePlayers(this.state.fastcupUserIds);
    handleClose();
  };

  render() {
    const { open, handleClose, stats } = this.props;
    return (
      <div>
        <Dialog open={open} onClose={handleClose} maxWidth="500px">
          <DialogTitle>Shuffle</DialogTitle>
          <DialogContent>
            <DialogContentText>
              To shuffle players, select 10 players from table.
            </DialogContentText>
            <DataGrid
              checkboxSelection
              className="shuffle-table"
              rows={stats}
              columns={columns}
              sortModel={[
                {
                  field: "rank",
                  sort: "desc",
                },
              ]}
              onSelectionModelChange={this.handleSelectionModelChange}
              selectionModel={this.state.fastcupUserIds}
              autoPageSize="true"
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={this.handleShuffleClick} color="primary">
              Shuffle
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

import React from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import PropTypes from "prop-types";
import "./style.css";
import { DIALOGS } from "../../reducers/dialogReducer";
import {
  Grid,
  LinearProgress,
  List,
  ListItem,
  ListItemText,
  ListSubheader,
  Paper,
} from "@material-ui/core";

class Alert extends React.Component {
  render() {
    return null;
  }
}

Alert.propTypes = {
  severity: PropTypes.string,
  children: PropTypes.node,
};
export default class ShuffleResultDialog extends React.Component {
  static propTypes = {
    isDialogOpen: PropTypes.bool.isRequired,
    leftTeam: PropTypes.objectOf(
      PropTypes.shape({
        id: PropTypes.number.isRequired,
        nickname: PropTypes.string.isRequired,
        rank: PropTypes.number.isRequired,
        kills: PropTypes.number.isRequired,
        deaths: PropTypes.number.isRequired,
        totalMatches: PropTypes.number.isRequired,
        wonMatches: PropTypes.number.isRequired,
      })
    ).isRequired,
    leftTeamAvgRank: PropTypes.number.isRequired,
    rightTeam: PropTypes.objectOf(
      PropTypes.shape({
        id: PropTypes.number.isRequired,
        nickname: PropTypes.string.isRequired,
        rank: PropTypes.number.isRequired,
        kills: PropTypes.number.isRequired,
        deaths: PropTypes.number.isRequired,
        totalMatches: PropTypes.number.isRequired,
        wonMatches: PropTypes.number.isRequired,
      })
    ).isRequired,
    rightTeamAvgRank: PropTypes.number.isRequired,
    isShuffleInProgress: PropTypes.bool.isRequired,
    isShuffleContainsError: PropTypes.bool.isRequired,
    closeDialog: PropTypes.func.isRequired,
  };

  state = {};

  handleCloseDialog = () => {
    this.props.closeDialog(DIALOGS.SHUFFLE_RESULT);
  };

  renderTeamList(team, avgRank) {
    return (
      <List
        component="nav"
        aria-labelledby="team-avg-subheader"
        subheader={
          <ListSubheader component="div" id="team-avg-subheader">
            Average rank: {avgRank}
          </ListSubheader>
        }
      >
        {team != null ? (
          Object.entries(team).map(([id, player]) => (
            <ListItem key={id}>
              <ListItemText primary={player.nickname} />
            </ListItem>
          ))
        ) : (
          <div>КАКАЯТОДИЧЬ</div>
        )}
      </List>
    );
  }

  render() {
    const {
      isDialogOpen,
      leftTeam,
      leftTeamAvgRank,
      rightTeam,
      rightTeamAvgRank,
      isShuffleInProgress,
      isShuffleContainsError,
    } = this.props;

    return (
      <div>
        <Dialog
          open={isDialogOpen}
          onClose={this.handleCloseDialog}
          maxWidth="lg"
          className="shuffle-result-dialog"
        >
          <DialogTitle>Shuffle result</DialogTitle>
          <DialogContent>
            <div className="shuffle-dialog-wrapper">
              {isShuffleInProgress ? (
                <LinearProgress />
              ) : isShuffleContainsError ? (
                <div>Выбери 10 человек дурень!</div>
              ) : (
                <Grid container spacing={3}>
                  <Grid item xs zeroMinWidth>
                    <Paper elevation={3}>
                      {this.renderTeamList(leftTeam, leftTeamAvgRank)}
                    </Paper>
                  </Grid>
                  <Grid item xs zeroMinWidth>
                    <Paper elevation={3}>
                      {this.renderTeamList(rightTeam, rightTeamAvgRank)}
                    </Paper>
                  </Grid>
                </Grid>
              )}
            </div>
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleCloseDialog} color="primary">
              Close
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

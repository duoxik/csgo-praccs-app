import { connect } from "react-redux";
import Index from "../components/ShuffleResultDialog";
import { bindActionCreators } from "redux";
import { closeDialog } from "../actions/dialogActions";

const mapStateToProps = ({ dialogReducer, shuffleReducer }) => ({
  isDialogOpen: dialogReducer.isShuffleResultDialogOpen,
  leftTeam: shuffleReducer.leftTeam,
  leftTeamAvgRank: shuffleReducer.leftTeamAvgRank,
  rightTeam: shuffleReducer.rightTeam,
  rightTeamAvgRank: shuffleReducer.rightTeamAvgRank,
  isShuffleInProgress: shuffleReducer.isShuffleInProgress,
  isShuffleContainsError: shuffleReducer.isShuffleContainsError,
});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ closeDialog }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Index);

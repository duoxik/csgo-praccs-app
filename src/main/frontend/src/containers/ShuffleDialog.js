import { connect } from "react-redux";
import Index from "../components/ShuffleDialog";
import { bindActionCreators } from "redux";
import { shufflePlayers } from "../actions/shuffleActions";
import { closeDialog, openDialog } from "../actions/dialogActions";

const mapStateToProps = ({ playerStatsReducer, dialogReducer }) => ({
  stats: playerStatsReducer.stats,
  isDialogOpen: dialogReducer.isShuffleDialogOpen,
});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ shufflePlayers, openDialog, closeDialog }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Index);

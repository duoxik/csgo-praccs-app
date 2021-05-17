import { connect } from "react-redux";
import Index from "../components/ShuffleDialog";
import { bindActionCreators } from "redux";
import { shufflePlayers } from "../actions/shuffleActions";

const mapStateToProps = ({ playerStatsReducer }) => ({
  stats: playerStatsReducer.stats,
});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ shufflePlayers }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Index);

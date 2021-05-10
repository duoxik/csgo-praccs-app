import { connect } from "react-redux";
import Index from "../components/PlayerStats";
import { bindActionCreators } from "redux";
import { loadAllStats } from "../actions/playerStatsActions";

const mapStateToProps = ({ playerStatsReducer }) => ({
  stats: playerStatsReducer.stats,
});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ loadAllStats }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Index);

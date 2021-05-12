import { connect } from "react-redux";
import Index from "../components/UploadMatchDialog";
import { bindActionCreators } from "redux";
import { uploadMatch } from "../actions/playerStatsActions";

const mapStateToProps = () => ({});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ uploadMatch }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Index);

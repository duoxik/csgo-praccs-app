import { connect } from "react-redux";
import Index from "../components/UploadMatchDialog";
import { bindActionCreators } from "redux";
import { uploadMatch } from "../actions/playerStatsActions";
import { closeDialog } from "../actions/dialogActions";

const mapStateToProps = ({ dialogReducer }) => ({
  isDialogOpen: dialogReducer.isUploadDialogOpen,
});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ uploadMatch, closeDialog }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Index);

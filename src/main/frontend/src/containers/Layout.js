import { connect } from "react-redux";
import Index from "../components/Layout";
import { bindActionCreators } from "redux";
import { openDialog } from "../actions/dialogActions";

const mapStateToProps = () => ({});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ openDialog }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Index);

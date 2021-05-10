import { connect } from "react-redux";
import Layout from "../components/Layout/Layout";
import { bindActionCreators } from "redux";
import { setHeaderTitle } from "../actions/headerActions";

const mapStateToProps = ({ chatReducer, profileReducer }) => ({
  chats: chatReducer.chats,
  userName: profileReducer.systemName,
});

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ setHeaderTitle }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Layout);

import { connect } from "react-redux";
import Index from "../components/Layout";
// import { bindActionCreators } from "redux";

const mapStateToProps = () => ({
  // chats: chatReducer.chats,
  // userName: profileReducer.systemName,
});

// const mapDispatchToProps = (dispatch) =>
//   bindActionCreators({ setHeaderTitle }, dispatch);

export default connect(mapStateToProps)(Index);

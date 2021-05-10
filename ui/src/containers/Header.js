import { connect } from "react-redux";
import Header from "../components/Header/Header";

const mapStateToProps = ({ headerReducer }) => ({
  title: headerReducer.title,
});

export default connect(mapStateToProps)(Header);

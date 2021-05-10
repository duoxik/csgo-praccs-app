import React from "react";
import PropTypes from "prop-types";
import Container from "@material-ui/core/Container";
import EmptyMessageField from "../EmptyChatField/EmptyMessageField";
import Header from "../../containers/Header";
import MessageField from "../../containers/MessageField";
import ChatList from "../../containers/ChatList";
import Profile from "../../containers/Profile";
import "./style.css";

export default class Layout extends React.Component {
  static propTypes = {
    display: PropTypes.string,
    userName: PropTypes.string,
    chats: PropTypes.object,
    chatId: PropTypes.string,
    setHeaderTitle: PropTypes.func,
  };

  getComponentForDisplay = () => {
    const { chatId, display } = this.props;
    switch (display) {
      case "chat":
        return <MessageField chatId={chatId} />;
      case "profile":
        return <Profile />;
      default:
        return <EmptyMessageField />;
    }
  };

  getHeaderText = () => {
    const { chats, chatId, display, userName } = this.props;
    switch (display) {
      case "chat":
        return chats[chatId].title;
      case "profile":
        return userName;
      default:
        return "Чат не выбран";
    }
  };

  render() {
    const { chatId, setHeaderTitle } = this.props;
    setHeaderTitle(this.getHeaderText());

    return (
      <Container className="layout">
        <Header />
        <div className="content">
          <ChatList chatId={chatId} />
          {this.getComponentForDisplay()}
        </div>
      </Container>
    );
  }
}

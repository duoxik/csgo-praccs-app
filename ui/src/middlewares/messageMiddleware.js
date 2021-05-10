import {
  disableChatHighlight,
  ENABLE_CHAT_HIGHLIGHT,
  enableChatHighlight,
  SEND_MESSAGE,
  sendMessage,
} from "../actions/chatActions";

export default (store) => (next) => (action) => {
  switch (action.type) {
    case SEND_MESSAGE:
      if (action.sender === "me") {
        setTimeout(
          () =>
            store.dispatch(
              sendMessage(action.chatId, "Не приставай ко мне, я робот!", "bot")
            ),
          1000
        );
      } else if (action.sender === "bot") {
        const chatHighlighted =
          store.getState().chatReducer.chats[action.chatId].highlight === true;
        if (!chatHighlighted) {
          store.dispatch(enableChatHighlight(action.chatId));
        }
      }
      break;
    case ENABLE_CHAT_HIGHLIGHT: {
      setTimeout(
        () => store.dispatch(disableChatHighlight(action.chatId)),
        3000
      );
      break;
    }
  }
  return next(action);
};

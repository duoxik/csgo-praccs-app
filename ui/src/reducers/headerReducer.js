import { SET_HEADER_TITLE } from "../actions/headerActions";

const initialStore = {
  title: "",
};

export default function headerReducer(store = initialStore, action) {
  switch (action.type) {
    case SET_HEADER_TITLE:
      return {
        title: action.title,
      };
    default:
      return store;
  }
}

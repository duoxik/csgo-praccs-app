import { CLOSE_DIALOG, OPEN_DIALOG } from "../actions/dialogActions";

export const DIALOGS = {
  UPLOAD: "UPLOAD",
  SHUFFLE: "SHUFFLE",
  SHUFFLE_RESULT: "SHUFFLE_RESULT",
};

const initialStore = {
  isUploadDialogOpen: false,
  isShuffleDialogOpen: false,
  isShuffleResultDialogOpen: false,
};

export default function dialogReducer(store = initialStore, action) {
  switch (action.type) {
    case OPEN_DIALOG: {
      switch (action.dialog) {
        case DIALOGS.UPLOAD: {
          return {
            ...store,
            isUploadDialogOpen: true,
          };
        }
        case DIALOGS.SHUFFLE: {
          return {
            ...store,
            isShuffleDialogOpen: true,
          };
        }
        case DIALOGS.SHUFFLE_RESULT: {
          return {
            ...store,
            isShuffleResultDialogOpen: true,
          };
        }
      }
    }
    case CLOSE_DIALOG: {
      switch (action.dialog) {
        case DIALOGS.UPLOAD: {
          return {
            ...store,
            isUploadDialogOpen: false,
          };
        }
        case DIALOGS.SHUFFLE: {
          return {
            ...store,
            isShuffleDialogOpen: false,
          };
        }
        case DIALOGS.SHUFFLE_RESULT: {
          return {
            ...store,
            isShuffleResultDialogOpen: false,
          };
        }
      }
    }
  }
  return store;
}

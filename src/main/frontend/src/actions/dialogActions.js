export const OPEN_DIALOG = "@@dialog/OPEN_DIALOG";
export const CLOSE_DIALOG = "@@dialog/CLOSE_DIALOG";

export const openDialog = (dialog) => ({
  type: OPEN_DIALOG,
  dialog,
});

export const closeDialog = (dialog) => ({
  type: CLOSE_DIALOG,
  dialog,
});

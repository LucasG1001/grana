export const updateInputs = (inputs, id, changes) => {
  return inputs.map((item) => {
    if (item.id === id) {
      return {
        ...item,
        ...changes,
      };
    }
    return item;
  });
};

import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

export interface ExpenseSplitDto {
  id: number;
  expenseId: number;
  amount: number;
  payeeId: number;
  payerId: number;
  isSettled: boolean;
}

interface SplitsState {
  splits: ExpenseSplitDto[];
  loading: boolean;
  error: string | null;
}

const initialState: SplitsState = {
  splits: [
    { id: 301, expenseId: 201, amount: 3000, payeeId: 1, payerId: 2, isSettled: false },
    { id: 302, expenseId: 201, amount: 3000, payeeId: 1, payerId: 3, isSettled: false },
    { id: 303, expenseId: 201, amount: 3000, payeeId: 1, payerId: 1, isSettled: true },
    { id: 304, expenseId: 202, amount: 1500, payeeId: 2, payerId: 1, isSettled: false },
    { id: 305, expenseId: 202, amount: 1500, payeeId: 2, payerId: 3, isSettled: false },
    { id: 306, expenseId: 202, amount: 1500, payeeId: 2, payerId: 2, isSettled: true },
    { id: 307, expenseId: 203, amount: 400, payeeId: 2, payerId: 1, isSettled: false },
    { id: 308, expenseId: 203, amount: 400, payeeId: 2, payerId: 4, isSettled: false },
    { id: 309, expenseId: 203, amount: 400, payeeId: 2, payerId: 2, isSettled: true },
  ],
  loading: false,
  error: null,
};

const splitsSlice = createSlice({
  name: 'splits',
  initialState,
  reducers: {
    setSplits: (state, action: PayloadAction<ExpenseSplitDto[]>) => {
      state.splits = action.payload;
    },
    addSplits: (state, action: PayloadAction<ExpenseSplitDto[]>) => {
      state.splits.push(...action.payload);
    },
    updateSplit: (state, action: PayloadAction<ExpenseSplitDto>) => {
      const index = state.splits.findIndex(split => split.id === action.payload.id);
      if (index !== -1) {
        state.splits[index] = action.payload;
      }
    },
    settleSplits: (state, action: PayloadAction<number[]>) => {
      state.splits.forEach(split => {
        if (action.payload.includes(split.id)) {
          split.isSettled = true;
        }
      });
    },
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loading = action.payload;
    },
    setError: (state, action: PayloadAction<string | null>) => {
      state.error = action.payload;
    },
  },
});

export const {
  setSplits,
  addSplits,
  updateSplit,
  settleSplits,
  setLoading,
  setError,
} = splitsSlice.actions;

export default splitsSlice.reducer;

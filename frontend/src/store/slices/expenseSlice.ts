import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

export interface ExpenseDto {
  id: number;
  title: string;
  description: string;
  amount: number;
  createdAt: Date;
  paidById: number;
  groupId: number;
  splits: number[];
}

interface ExpensesState {
  expenses: ExpenseDto[];
  loading: boolean;
  error: string | null;
}

const initialState: ExpensesState = {
  expenses: [
    {
      id: 201,
      title: "Hotel Stay",
      description: "3 nights at Taj",
      amount: 9000,
      createdAt: new Date('2024-01-16'),
      paidById: 1,
      groupId: 101,
      splits: [301, 302, 303],
    },
    {
      id: 202,
      title: "Car Rental",
      description: "Taxi for sightseeing",
      amount: 4500,
      createdAt: new Date('2024-01-17'),
      paidById: 2,
      groupId: 101,
      splits: [304, 305, 306],
    },
    {
      id: 203,
      title: "Pizza Lunch",
      description: "Team lunch at office",
      amount: 1200,
      createdAt: new Date('2024-02-01'),
      paidById: 2,
      groupId: 102,
      splits: [307, 308, 309],
    },
  ],
  loading: false,
  error: null,
};

const expensesSlice = createSlice({
  name: 'expenses',
  initialState,
  reducers: {
    setExpenses: (state, action: PayloadAction<ExpenseDto[]>) => {
      state.expenses = action.payload;
    },
    addExpense: (state, action: PayloadAction<ExpenseDto>) => {
      state.expenses.push(action.payload);
    },
    updateExpense: (state, action: PayloadAction<ExpenseDto>) => {
      const index = state.expenses.findIndex(expense => expense.id === action.payload.id);
      if (index !== -1) {
        state.expenses[index] = action.payload;
      }
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
  setExpenses,
  addExpense,
  updateExpense,
  setLoading,
  setError,
} = expensesSlice.actions;

export default expensesSlice.reducer;

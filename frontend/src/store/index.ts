import { configureStore } from '@reduxjs/toolkit';
import authSlice from './slices/authSlice';
import groupsSlice from './slices/groupSlice';
import expensesSlice from './slices/expenseSlice';
import splitsSlice from './slices/splitSlice';
import settlementsSlice from './slices/settlementSlice';

export const store = configureStore({
  reducer: {
    auth: authSlice,
    groups: groupsSlice,
    expenses: expensesSlice,
    splits: splitsSlice,
    settlements: settlementsSlice,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: ['persist/PERSIST'],
      },
    }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
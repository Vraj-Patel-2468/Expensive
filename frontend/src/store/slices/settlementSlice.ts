import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

export interface SettlementDto {
  id: number;
  payeeId: number;
  payerId: number;
  amount: number;
  createdAt?: Date;
}

interface SettlementsState {
  settlements: SettlementDto[];
  loading: boolean;
  error: string | null;
}

const initialState: SettlementsState = {
  settlements: [
    { id: 401, payeeId: 1, payerId: 2, amount: 1500, createdAt: new Date('2024-01-20') },
  ],
  loading: false,
  error: null,
};

const settlementsSlice = createSlice({
  name: 'settlements',
  initialState,
  reducers: {
    setSettlements: (state, action: PayloadAction<SettlementDto[]>) => {
      state.settlements = action.payload;
    },
    addSettlement: (state, action: PayloadAction<SettlementDto>) => {
      state.settlements.push(action.payload);
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
  setSettlements,
  addSettlement,
  setLoading,
  setError,
} = settlementsSlice.actions;

export default settlementsSlice.reducer;

import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

export interface UserDto {
  id: number;
  username: string;
  email: string;
  password: string;
  isEnabled: boolean;
  createdAt: Date;
  updatedAt: Date;
  groupsCreated: number[];
  groupsJoined: number[];
  expensesPaid: number[];
  splitsToPay: number[];
  splitsToCollect: number[];
  settlementsToPay: number[];
  settlementsToCollect: number[];
}

interface AuthState {
  user: UserDto | null;
  token: string | null;
  isAuthenticated: boolean;
  loading: boolean;
  error: string | null;
}

const initialState: AuthState = {
  user: {
    id: 1,
    username: "vraj",
    email: "vraj@example.com",
    password: "",
    isEnabled: true,
    createdAt: new Date(),
    updatedAt: new Date(),
    groupsCreated: [101],
    groupsJoined: [101, 102],
    expensesPaid: [201],
    splitsToPay: [301],
    splitsToCollect: [302, 303],
    settlementsToPay: [],
    settlementsToCollect: [401],
  },
  token: "dummy-token",
  isAuthenticated: true,
  loading: false,
  error: null,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    loginStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    loginSuccess: (state, action: PayloadAction<{ user: UserDto; token: string }>) => {
      state.loading = false;
      state.user = action.payload.user;
      state.token = action.payload.token;
      state.isAuthenticated = true;
      state.error = null;
    },
    loginFailure: (state, action: PayloadAction<string>) => {
      state.loading = false;
      state.error = action.payload;
      state.isAuthenticated = false;
    },
    logout: (state) => {
      state.user = null;
      state.token = null;
      state.isAuthenticated = false;
      state.error = null;
    },
    signupStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    signupSuccess: (state, action: PayloadAction<{ user: UserDto; token: string }>) => {
      state.loading = false;
      state.user = action.payload.user;
      state.token = action.payload.token;
      state.isAuthenticated = true;
      state.error = null;
    },
    signupFailure: (state, action: PayloadAction<string>) => {
      state.loading = false;
      state.error = action.payload;
    },
    clearError: (state) => {
      state.error = null;
    },
  },
});

export const {
  loginStart,
  loginSuccess,
  loginFailure,
  logout,
  signupStart,
  signupSuccess,
  signupFailure,
  clearError,
} = authSlice.actions;

export default authSlice.reducer;

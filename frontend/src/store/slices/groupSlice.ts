import { createSlice, type PayloadAction } from "@reduxjs/toolkit";

export interface GroupDto {
  id: number;
  name: string;
  createdAt: Date;
  createdBy: number;
  members: number[];
  expenses: number[];
}

interface GroupsState {
  groups: GroupDto[];
  currentGroup: GroupDto | null;
  loading: boolean;
  error: string | null;
}


const initialState: GroupsState = {
  groups: [
    {
      id: 101,
      name: "Goa Trip",
      createdAt: new Date('2024-01-15'),
      createdBy: 1,
      members: [1, 2, 3],
      expenses: [201, 202],
    },
    {
      id: 102,
      name: "Office Lunch",
      createdAt: new Date('2024-02-01'),
      createdBy: 2,
      members: [1, 2, 4],
      expenses: [203],
    },
  ],
  currentGroup: null,
  loading: false,
  error: null,
};


const groupsSlice = createSlice({
  name: 'groups',
  initialState,
  reducers: {
    setGroups: (state, action: PayloadAction<GroupDto[]>) => {
      state.groups = action.payload;
    },
    addGroup: (state, action: PayloadAction<GroupDto>) => {
      state.groups.push(action.payload);
    },
    setCurrentGroup: (state, action: PayloadAction<GroupDto | null>) => {
      state.currentGroup = action.payload;
    },
    updateGroup: (state, action: PayloadAction<GroupDto>) => {
      const index = state.groups.findIndex(group => group.id === action.payload.id);
      if (index !== -1) {
        state.groups[index] = action.payload;
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
  setGroups,
  addGroup,
  setCurrentGroup,
  updateGroup,
  setLoading,
  setError,
} = groupsSlice.actions;

export default groupsSlice.reducer;

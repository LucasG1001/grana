export interface LoginState {
  ok: boolean;
  error: string;
  data: any | null;
}

export const initialState: LoginState = {
  ok: false,
  error: '',
  data: null,
};

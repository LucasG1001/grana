import axios from 'axios';
export default function apiError(error: unknown): LoginState {
  if (axios.isAxiosError(error)) {
    return {
      data: error.response?.data ?? null,
      ok: false,
      error:
        error.response?.data?.message || error.message || 'Erro desconhecido',
    };
  } else {
    console.log(error);
    return {
      data: null,
      ok: false,
      error: String(error) || 'Erro desconhecido',
    };
  }
}

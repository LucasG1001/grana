'use server';

import api from '@/api/axios';
import axios from 'axios';
import { cookies } from 'next/headers';
import apiError from '../apiError';
import { LoginState } from '../types';

interface LoginResponse {
  token: string;
  user: {
    name: string;
    email: string;
  };
}

export default async function login(
  state: LoginState,
  formData: FormData,
): Promise<LoginState> {
  const email = formData.get('email');
  const password = formData.get('password');

  try {
    const response = await api.post('/auth/login', { email, password });
    console.log(response.data);
    (await cookies()).set('acessToken', response.data.acessToken, {
      httpOnly: true,
      secure: true,
      sameSite: 'lax',
      maxAge: 60 * 60 * 24,
    });
    return {
      ok: true,
      error: '',
      data: response.data,
    };
  } catch (error) {
    console.log(error);

    return apiError(error);
  }
}

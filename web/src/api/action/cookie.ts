'use server';

import { cookies } from 'next/headers';

async function setCookie(key: string, value: string): Promise<void> {
  (await cookies()).set(key, value, {
    httpOnly: true,
    secure: true,
    path: '/',
    maxAge: 60 * 60 * 24 * 7,
  });
}

async function getCookie(key: string): Promise<string | undefined> {
  const cookie = (await cookies()).get(key);
  return cookie?.value;
}

async function deleteCookie(key: string): Promise<void> {
  (await cookies()).delete(key);
}

export { setCookie, getCookie, deleteCookie };

import axios from 'axios';
import moment from 'moment';
import cookies from 'next-cookies';

moment.locale('nb');

export const fetcher = (url: string) =>
  fetch(url, {
    credentials: 'include',
  }).then((res) => res.json());

export const fetchBuilder = (url: string, cookie: string | undefined, method: 'GET' | 'POST' | 'PUT' | 'DELETE' = 'GET') =>
  axios(url, {
    method: method,
    headers: {
      Cookie: cookie,
    },
  });

export const cookieHandler = (cookies: string | undefined, cookie: string): string | undefined => {
  if (cookies) {
    try {
      return cookies.split('; ').find((c) => c.startsWith(cookie));
    } catch (err) {
      return undefined;
    }
  }
  return undefined;
};

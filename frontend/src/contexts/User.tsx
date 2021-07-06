import router from 'next/dist/client/router';
import { createContext, useContext, useEffect } from 'react';
import useSWR, { SWRResponse } from 'swr';
import { IUser } from 'utils/types';
import { fetcher } from 'utils/utils';

const UserContext = createContext(undefined);

function useUser(): IUser | undefined {
  return useContext(UserContext);
}

function UserProvider(props: any) {
  const { data: user, isValidating }: SWRResponse<IUser, any> = useSWR(`${process.env.API_URL}users`, fetcher);

  useEffect(() => {
    if (!user && !isValidating) {
      router.push('/login');
    }
  }, [user]);

  return <UserContext.Provider value={user} {...props} />;
}
export { UserProvider, useUser };

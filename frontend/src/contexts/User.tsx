import { createContext, useContext, useEffect } from 'react';
import useSWR from 'swr';
import { IUser } from 'utils/types';
import { fetcher } from 'utils/utils';
import router from 'next/dist/client/router';

const UserContext = createContext(undefined);

function useUser(): (IUser | undefined){
  return useContext(UserContext);
}

function UserProvider(props: any) {
  const { data: user, isLoading } = useSWR(`${process.env.API_URL}users`, fetcher);

  useEffect(() => {
    if(!user && ! isLoading) {
      router.push("/login")
    }
  }, [user])

  return <UserContext.Provider value={user} {...props} />;
}
export { UserProvider, useUser };

import { gql, useQuery } from '@apollo/client';
import router from 'next/dist/client/router';
import { createContext, useContext, useEffect } from 'react';
import { IUser } from 'utils/types';

const UserContext = createContext(undefined);

function useUser(): IUser | undefined {
  return useContext(UserContext);
}

const LOGGED_IN_PROFILE = gql`
  query {
    profile {
      id
      given_name
      family_name
      email
      picture
    }
  }
`;

// eslint-disable-next-line @typescript-eslint/no-explicit-any
function UserProvider(props: any) {
  const { data: user, loading } = useQuery(LOGGED_IN_PROFILE);

  useEffect(() => {
    if (!user?.profile && !loading) {
      router.push('/login');
    }
  }, [user?.profile, loading]);

  return <UserContext.Provider value={user?.profile} {...props} />;
}
export { UserProvider, useUser };

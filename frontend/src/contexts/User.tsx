import { gql, useQuery } from '@apollo/client';
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

function UserProvider(props: any) {
  const { data: user } = useQuery(LOGGED_IN_PROFILE);

  return <UserContext.Provider value={user?.profile} {...props} />;
}
export { UserProvider, useUser };

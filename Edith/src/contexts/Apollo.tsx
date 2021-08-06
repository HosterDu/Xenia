import { ApolloClient, ApolloProvider as ApolloClientProvider, createHttpLink, InMemoryCache } from '@apollo/client';
import { ReactNode } from 'react';

const link = createHttpLink({
  uri: `${process.env.API_URL}graphql`,
  credentials: 'include',
});

export const client = new ApolloClient({
  cache: new InMemoryCache(),
  link,
});
const ApolloProvider = ({ children }: { children: ReactNode }) => {
  return <ApolloClientProvider client={client}>{children}</ApolloClientProvider>;
};

export default ApolloProvider;

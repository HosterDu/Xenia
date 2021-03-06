import { ChakraProvider } from '@chakra-ui/react';
import ApolloProvider from 'contexts/Apollo';
import { UserProvider } from 'contexts/User';
import React, { ReactNode } from 'react';
import theme from 'theme';

const Context = ({ children }: { children: ReactNode }) => {
  return (
    <>
      <ApolloProvider>
        <UserProvider>
          <ChakraProvider theme={theme}>{children}</ChakraProvider>
        </UserProvider>
      </ApolloProvider>
    </>
  );
};

export default Context;

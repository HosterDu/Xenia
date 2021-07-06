import { SnackbarProvider } from 'contexts/Snackbar';
import { UserProvider } from 'contexts/User';
import { ChakraProvider } from "@chakra-ui/react"
import React, { ReactNode } from 'react';
import theme from 'theme';

const Context = ({ children }: { children: ReactNode }) => {
  return (
    <>
      <UserProvider>
        <ChakraProvider theme={theme}>
          <SnackbarProvider>{children}</SnackbarProvider>
        </ChakraProvider>
      </UserProvider>
    </>
  );
};

export default Context;

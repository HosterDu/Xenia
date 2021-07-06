import { extendTheme } from '@chakra-ui/react';


const theme = extendTheme({
  config: {
    initialColorMode: 'light',
    useSystemColorMode: true,
  },
  colors: {
    brand: {},
  },
});
export default theme;

import { extendTheme } from '@chakra-ui/react';

const theme = extendTheme({
  config: {
    initialColorMode: 'dark',
    useSystemColorMode: false,
  },
  components: {
    Input: {
      baseStyle: {
        field: {
          '::-webkit-calendar-picker-indicator': {
            filter: 'invert(1)',
          },
        },
      },
    },
  },
});

export default theme;

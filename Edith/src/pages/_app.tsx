import Context from 'context';
import type { AppProps } from 'next/app';

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <Context>
      <Component {...pageProps} />
    </Context>
  );
}
export default MyApp;

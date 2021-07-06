import { Container, Grid, Heading } from '@chakra-ui/react';
import { useUser } from 'contexts/User';
import router from 'next/dist/client/router';
import Head from 'next/head';
import { useEffect } from 'react';
import { GoogleLoginButton } from 'react-social-login-buttons';

const Login = () => {
  const user = useUser();
  useEffect(() => {
    if (user?.id) {
      router.push('/');
    }
  }, [user]);

  return (
    <>
      <Head>
        <title>Login</title>
        <meta content='Login to Xenia' name='description' />
        <link href='/favicon.ico' rel='icon' />
      </Head>
      <Container centerContent>
        <Grid
          autoFlow='row dense'
          bg='tomato'
          borderRadius='lg'
          borderWidth='1px'
          display='flex'
          flexDirection='column'
          gap='20px'
          marginTop='30vh'
          maxW='sm'
          overflow='hidden'
          padding='20px'>
          <Heading size='lg' textAlign='center'>
            Logg inn
          </Heading>
          <GoogleLoginButton onClick={() => (location.href = `${process.env.API_URL}login/google`)} text='med Google' />
        </Grid>
      </Container>
    </>
  );
};

export default Login;

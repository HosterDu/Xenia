import Head from 'next/head';
import { Heading, Grid, Container } from '@chakra-ui/react';
import { GoogleLoginButton } from 'react-social-login-buttons';
import { useUser } from 'contexts/User';
import { useEffect } from 'react';
import router from 'next/dist/client/router';

const Login = () => {
  const user = useUser();

  useEffect(()=>{
    if(user?.id) {
        router.push("/")
    }
  }, [user])

  return (
    <>
      <Head>
        <title>Login</title>
        <meta name='description' content='Login to Xenia' />
        <link rel='icon' href='/favicon.ico' />
      </Head>
      <Container centerContent>
        <Grid
          gap='20px'
          autoFlow='row dense'
          maxW='sm'
          borderWidth='1px'
          padding='20px'
          borderRadius='lg'
          overflow='hidden'
          bg='tomato'
          display='flex'
          marginTop='30vh'
          flexDirection='column'>
          <Heading size='lg' textAlign='center'>
            Logg inn
          </Heading>
          <GoogleLoginButton text='med Google' onClick={() => (location.href = `${process.env.API_URL}login/google`)} />
        </Grid>
      </Container>
    </>
  );
};

export default Login;

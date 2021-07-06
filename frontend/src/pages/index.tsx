import { AddIcon } from '@chakra-ui/icons';
import { Container, Grid, Heading, IconButton } from '@chakra-ui/react';
import Card from 'components/index/Card';
import { useUser } from 'contexts/User';
import { GetServerSideProps, InferGetServerSidePropsType } from 'next';
import router from 'next/dist/client/router';
import Head from 'next/head';
import { IEvent } from 'utils/types';
import { cookieHandler, fetchBuilder } from 'utils/utils';

export const getServerSideProps: GetServerSideProps = async (context) => {
  try {
    const cookies = context.req.headers.cookie;
    const user_session_cookie = cookieHandler(cookies, 'user_session');
    const res = await fetchBuilder(`events`, user_session_cookie);
    const events: IEvent[] = await res.data;
    return {
      props: { events },
    };
  } catch (error) {
    return {
      redirect: {
        destination: '/login',
        permanent: false,
      },
    };
  }
};

const Dashboard = ({ events }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const user = useUser();
  return (
    <>
      <Head>
        <title>Xenia</title>
        <meta content='Generated by create next app' name='description' />
        <link href='/favicon.ico' rel='icon' />
      </Head>
      <Container maxW='container.lg'>
        <Heading m='25px 0' size='2xl'>
          My Events <IconButton aria-label='Add event' colorScheme='teal' icon={<AddIcon />} isRound onClick={() => router.push('/event')} variant='outline' />
        </Heading>
        <Container centerContent maxW='container.xl'>
          <Grid gap={6} templateColumns='repeat(3, 1fr)'>
            {events?.map((event: IEvent) => (
              <Card event={event} key={event.id} />
            ))}
          </Grid>
        </Container>
      </Container>
    </>
  );
};

export default Dashboard;

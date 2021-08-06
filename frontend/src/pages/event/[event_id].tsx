import { gql } from '@apollo/client';
import { ArrowBackIcon } from '@chakra-ui/icons';
import { AspectRatio, Box, Container, Divider, Heading, IconButton, Image } from '@chakra-ui/react';
import { client } from 'contexts/Apollo';
import { GetServerSideProps, InferGetServerSidePropsType } from 'next';
import router from 'next/dist/client/router';
import Head from 'next/head';

const EVENT_BY_ID = gql`
  query GetEventById($id: String!) {
    event(id: $id) {
      id
      title
      description
      location {
        id
        lat
        lng
      }
      startDate
      picture
      creator {
        id
        given_name
        family_name
      }
    }
  }
`;

export const getServerSideProps: GetServerSideProps = async (context) => {
  const eventId = context.params?.event_id;

  try {
    const { data } = await client.query({
      query: EVENT_BY_ID,
      variables: { id: eventId },
    });
    const event = await data?.event;
    return {
      props: { event },
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
const Event = ({ event }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  return (
    <div>
      <Head>
        <title>{event.title}</title>
        <meta content='Generated by create next app' name='description' />
        <link href='/favicon.ico' rel='icon' />
      </Head>
      <Container maxW='container.lg' mt='25px'>
        <IconButton aria-label='Add event' colorScheme='teal' icon={<ArrowBackIcon />} isRound onClick={() => router.push('/')} variant='outline' />
        <Box borderRadius='lg' borderWidth='1px' mt={5} overflow='hidden'>
          <AspectRatio ratio={32 / 9} width={'fill-content'}>
            <Image alt={event.title} src={event.picture} />
          </AspectRatio>
          <Box p='6'>
            <Box as='h4' fontWeight='semibold' isTruncated lineHeight='tight' mt='1'>
              <Heading>{event.title}</Heading>
            </Box>
            <Box alignItems='baseline' d='flex'>
              <Box color='gray.500' fontSize='sm' fontWeight='semibold' letterSpacing='wide' mt={3} textTransform='uppercase'>
                {`${event.location} - ${event.start_date_time}`}
              </Box>
            </Box>
            <Divider />
            <Box alignItems='baseline' d='flex'>
              <Box fontSize='sm' fontWeight='semibold' letterSpacing='wide' mt={3}>
                {event.description}
              </Box>
            </Box>
          </Box>
        </Box>
      </Container>
    </div>
  );
};

export default Event;

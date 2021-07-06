import { ArrowBackIcon } from '@chakra-ui/icons';
import { Button, Container, Heading, IconButton } from '@chakra-ui/react';
import axios from 'axios';
import Input from 'components/form/Input';
import router from 'next/dist/client/router';
import Head from 'next/head';
import { useForm } from 'react-hook-form';

const Event = () => {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
  } = useForm();

  function onSubmit(values: any) {
    axios.post(`events`, values);
  }

  return (
    <div>
      <Head>
        <title>Creating event</title>
        <meta content='Generated by create next app' name='description' />
        <link href='/favicon.ico' rel='icon' />
      </Head>
      <Container maxW='container.lg' mt='25px'>
        <Heading m='25px 0' size='2xl'>
          <IconButton aria-label='Add event' colorScheme='teal' icon={<ArrowBackIcon />} isRound onClick={() => router.push('/')} variant='outline' /> Create
          Event
        </Heading>
        <form onSubmit={handleSubmit(onSubmit)}>
          <Input errors={errors} name='title' placeholder='Coachella Party' register={register} title='Event title' />
          <Input errors={errors} name='location' placeholder='Slottsplassen 1, 0010 Oslo' register={register} title='Event location' />
          <Input errors={errors} name='picture' placeholder='https://coolimage.png' register={register} title='Event picture' />
          <Input errors={errors} multiline name='description' placeholder='This party is gonna be lit...' register={register} title='Event description' />
          <Input errors={errors} name='start_date_time' placeholder='2022-03-13T12:00' register={register} title='Event datetime' />
          <Button colorScheme='teal' isLoading={isSubmitting} mt={4} type='submit'>
            Submit
          </Button>
        </form>
      </Container>
    </div>
  );
};

export default Event;

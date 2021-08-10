import { Button } from '@chakra-ui/button';
import DateTime from 'components/form/DateTime';
import Input from 'components/form/Input';
import Location from 'components/form/Location';
import { FieldErrors, FieldValues, UseFormRegister } from 'react-hook-form';
import { formatMomentFromZoneTimeDate } from 'utils/date';
import { IEvent } from 'utils/types';

type EventFormType = {
  errors: FieldErrors;
  event?: IEvent;
  register: UseFormRegister<FieldValues>;
  getValues: any;
  setValue: (string, any) => void;
  isSubmitting: boolean;
};

const EventForm = ({ errors, event = undefined, register, getValues, setValue, isSubmitting }: EventFormType) => {
  return (
    <>
      <Input errors={errors} name='title' placeholder='Coachella Party' register={register} title='Event title' />
      <Location errors={errors} event={event} name='location' setValue={setValue} title='Event Location' />
      <Input errors={errors} name='picture' placeholder='https://coolimage.png' register={register} title='Event picture' />
      <Input errors={errors} multiline name='description' placeholder='This party is gonna be lit...' register={register} title='Event description' />
      <DateTime errors={errors} name='startDate' register={register} title='Event datetime start' />
      <DateTime
        errors={errors}
        name='endDate'
        register={register}
        rules={{
          validate: {
            afterStart: (v) => formatMomentFromZoneTimeDate(v).isAfter(formatMomentFromZoneTimeDate(getValues('startDate'))) || 'Has to be after start date',
          },
        }}
        title='Event datetime end'
      />
      <Button colorScheme='teal' isLoading={isSubmitting} mt={4} type='submit'>
        Submit
      </Button>
    </>
  );
};

export default EventForm;

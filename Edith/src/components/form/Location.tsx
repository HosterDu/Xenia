import { FormControl, FormErrorMessage, FormLabel, Input as CInput } from '@chakra-ui/react';
import { usePlacesWidget } from 'react-google-autocomplete';
import { FieldErrors } from 'react-hook-form';
import { IEvent } from 'utils/types';

type LocationType = {
  name: string;
  title: string;
  setValue: (string, any) => void;
  errors: FieldErrors;
  event?: IEvent;
};

const Location = ({ name, title, errors, setValue, event = undefined }: LocationType) => {
  const { ref } = usePlacesWidget({
    apiKey: process.env.GOOGLE_API_KEI,
    onPlaceSelected: (place) => {
      setValue('locationId', place.place_id);
      setValue('address', place.formatted_address);
      setValue('lat', place.geometry.location.lat());
      setValue('lng', place.geometry.location.lng());
    },
    options: {
      types: ['address'],
    },
  });

  return (
    <FormControl isInvalid={errors[name]}>
      <FormLabel htmlFor={name} id={name}>
        {title}
      </FormLabel>
      <CInput defaultValue={event?.location?.address} id={name} ref={ref} />
      <FormErrorMessage>{errors[name] && errors[name].message}</FormErrorMessage>
    </FormControl>
  );
};
export default Location;

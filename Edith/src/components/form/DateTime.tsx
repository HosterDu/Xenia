import { FormControl, FormErrorMessage, FormLabel, Input as CInput } from '@chakra-ui/react';
import { FieldErrors, RegisterOptions } from 'react-hook-form';

type DateTimeType = {
  name: string;
  title: string;
  register: any;
  errors: FieldErrors;
  rules?: RegisterOptions;
};

const DateTime = ({ name, title, register, errors, rules = undefined }: DateTimeType) => {
  return (
    <FormControl isInvalid={errors[name]}>
      <FormLabel htmlFor={name}>{title}</FormLabel>
      <CInput id={name} type='datetime-local' {...register(name, rules)} />
      <FormErrorMessage>{errors[name] && errors[name].message}</FormErrorMessage>
    </FormControl>
  );
};
export default DateTime;

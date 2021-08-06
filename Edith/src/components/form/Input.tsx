import { FormControl, FormErrorMessage, FormLabel, Input as CInput, Textarea } from '@chakra-ui/react';
import { FieldErrors, FieldValues, RegisterOptions, UseFormRegister } from 'react-hook-form';

type InputType = {
  name: string;
  placeholder: string;
  title: string;
  register: UseFormRegister<FieldValues>;
  errors: FieldErrors;
  rules?: RegisterOptions;
  multiline?: boolean;
};

const Input = ({ name, title, placeholder, register, errors, rules = undefined, multiline = false }: InputType) => {
  return (
    <FormControl isInvalid={errors.name}>
      <FormLabel htmlFor={name}>{title}</FormLabel>
      {multiline ? (
        <Textarea id={name} placeholder={placeholder} {...register(name, rules)} />
      ) : (
        <CInput id={name} placeholder={placeholder} {...register(name, rules)} />
      )}
      <FormErrorMessage>{errors.name && errors.name.message}</FormErrorMessage>
    </FormControl>
  );
};
export default Input;

import { Badge, Box, Image } from '@chakra-ui/react';
import router from 'next/dist/client/router';
import { IEvent } from 'utils/types';

const Card = ({ event }: { event: IEvent }) => {
  return (
    <Box
      _hover={{ border: '1px solid yellow', cursor: 'pointer' }}
      borderRadius='lg'
      borderWidth='1px'
      maxW='sm'
      onClick={() => router.push(`/event/${event.id}`)}
      overflow='hidden'>
      <Image alt={event.title} src={event.picture} />
      <Box p='6'>
        <Box alignItems='baseline' d='flex'>
          <Box color='gray.500' fontSize='xs' fontWeight='semibold' letterSpacing='wide' textTransform='uppercase'>
            {`${event.location} - ${event.start_date_time}`}
          </Box>
        </Box>

        <Box as='h4' fontWeight='semibold' isTruncated lineHeight='tight' mt='1'>
          {event.title}
        </Box>

        <Box alignItems='center' d='flex' mt='2'>
          <Badge borderRadius='full' colorScheme='teal' mr='10px' px='2'>
            52 Approved
          </Badge>
          <Badge borderRadius='full' colorScheme='teal' px='2'>
            5 days left
          </Badge>
        </Box>
      </Box>
    </Box>
  );
};

export default Card;

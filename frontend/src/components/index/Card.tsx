import { Box, Image, Badge } from '@chakra-ui/react';
import { IEvent } from 'utils/types';
import router from 'next/dist/client/router';

const Card = ({ event }: { event: IEvent }) => {
  return (
    <Box onClick={()=>router.push(`/event/${event.id}`)} maxW='sm' borderWidth='1px' borderRadius='lg' overflow='hidden' _hover={{ border: '1px solid yellow', cursor: 'pointer' }} >
      <Image src={event.picture} alt={event.title} />
      <Box p='6'>
        <Box d='flex' alignItems='baseline'>
          <Box color='gray.500' fontWeight='semibold' letterSpacing='wide' fontSize='xs' textTransform='uppercase'>
            {`${event.location} - ${event.start_date_time}`}
          </Box>
        </Box>

        <Box mt='1' fontWeight='semibold' as='h4' lineHeight='tight' isTruncated>
          {event.title}
        </Box>

        <Box d='flex' mt='2' alignItems='center'>
          <Badge borderRadius='full' px='2' colorScheme='teal' mr='10px'>
            52 Approved
          </Badge>
          <Badge borderRadius='full' px='2' colorScheme='teal'>
            5 days left
          </Badge>
        </Box>
      </Box>
    </Box>
  );
};

export default Card;

import { Badge, Box, Image } from '@chakra-ui/react';
import moment from 'moment';
import router from 'next/dist/client/router';
import { formatedDateTimeFromString, formatMomentFromZoneTimeDate } from 'utils/date';
import { IEvent } from 'utils/types';

const Card = ({ event }: { event: IEvent }) => {
  const daysUntilEvent = formatMomentFromZoneTimeDate(event.startDate).diff(moment(), 'days');

  return (
    <Box
      _hover={{ border: '1px solid yellow', cursor: 'pointer' }}
      borderRadius='lg'
      borderWidth='1px'
      maxW='sm'
      onClick={() => router.push(`/event/${event.id}`)}
      overflow='hidden'>
      <Image alt={event.title} src={event.picture || 'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg'} />
      <Box p='6'>
        <Box alignItems='baseline' d='flex'>
          <Box color='gray.500' fontSize='xs' fontWeight='semibold' letterSpacing='wide' textTransform='uppercase'>
            {`${event.location.address} - ${formatedDateTimeFromString(event.startDate)}`}
          </Box>
        </Box>

        <Box as='h4' fontWeight='semibold' isTruncated lineHeight='tight' mt='1'>
          {event.title}
        </Box>

        <Box alignItems='center' d='flex' mt='2'>
          <Badge borderRadius='full' colorScheme='teal' mr='10px' px='2'>
            52 Approved
          </Badge>

          {daysUntilEvent >= 0 && <Badge borderRadius='full' colorScheme='teal' px='2'>{`${daysUntilEvent} days`}</Badge>}
        </Box>
      </Box>
    </Box>
  );
};

export default Card;

import moment, { Moment } from 'moment';

moment.locale('nb');
export const formatMomentFromZoneTimeDate = (object: string) => {
  return moment(object.substr(0, 22));
};
export const formatedDateTimeFromMoment = (datetime: Moment) => {
  return datetime.format('Do MMMM YYYY, HH:mm');
};

export const formatedDateTimeFromString = (datetime: string) => {
  return formatMomentFromZoneTimeDate(datetime).format('Do MMMM YYYY, HH:mm');
};

export enum UserRole {
  REGISTERED,
  GUEST,
}

export type IUser = {
  id: string;
  given_name: string;
  family_name: string;
  email: string;
  picture: string;
  type?: UserRole;
};

export type IEvent = {
  id: string;
  title: string;
  description: string;
  start_date_time: Date;
  location: string;
  picture: string;
  createdById: IUser['id'];
};

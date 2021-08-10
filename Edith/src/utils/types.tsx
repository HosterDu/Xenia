export enum UserRole {
  REGISTERED,
  GUEST,
}

export type IUser = {
  id?: string;
  given_name?: string;
  family_name?: string;
  email?: string;
  picture?: string;
  type?: UserRole;
};

export type IEvent = {
  id?: string;
  title?: string;
  description?: string;
  startDate?: string;
  endDate?: string;
  location?: IGeoLocation;
  picture?: string;
  createdById?: IUser['id'];
};

export type IGeoLocation = {
  id?: string;
  address?: string;
  lat?: number;
  lng?: number;
};

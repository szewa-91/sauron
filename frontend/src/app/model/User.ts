import { Bank } from '~/app/model/Bank';

export interface User {
  id: number;
  username: string;
  email: string;
  banks: Array<Bank>;
}

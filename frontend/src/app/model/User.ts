import { Bank } from '~/app/model/Bank';

export class User {

  constructor(id: number, username: string, email: string, banks: Array<Bank>) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.banks = banks;
  }

  id: number;
  username: string;
  email: string;
  banks: Array<Bank>;

  public static instantiate(rawObject: User) {
    return new User(rawObject.id, rawObject.username, rawObject.email, rawObject.banks.map(Bank.instantiate));
  }
}

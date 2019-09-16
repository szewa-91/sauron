export class Bank {

  constructor(rawObject) {
    this.id = rawObject.id;
    this.name = rawObject.name;
    this.color = rawObject.color;
  }

  id: number;
  name: string;
  color: string;

  public static instantiate(rawObject) {
    return new Bank(rawObject);
  }
}

export class Bank {

  constructor(rawObject) {
    this.id = rawObject.id;
    this.name = rawObject.name;
    this.color = BankColor[rawObject.color as string];
  }

  id: number;
  name: string;
  color: BankColor;
}

export enum BankColor {
  ORANGE,
  LIGHT_BLUE,
  YELLOW
}

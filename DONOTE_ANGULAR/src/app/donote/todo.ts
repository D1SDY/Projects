//Todo class
export class Todo {
    // id: number;
    // todo: string;

    constructor(
      public text:string,
      public id:number,
      public date:number=Date.now(),
      public deadline:number,
      public done:Boolean
    ){}
  }
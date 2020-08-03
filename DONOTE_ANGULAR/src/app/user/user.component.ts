import { Component, OnInit } from '@angular/core';
import {User} from './user';

//Function wich will calculate the length of svg
declare function calcLenght():any;

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    calcLenght();
  }
  user:User={
    id:1,
    name:"Incognito"
  };


}

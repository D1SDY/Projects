import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';
import {TODOS} from './ListOfTodos';
import {Todo} from './todo';
import { formatDate } from '@angular/common';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import {MatSnackBar} from '@angular/material/snack-bar';
import { isNumber } from 'util';
//function wich will play the sound

declare function playAuido():any;

@Component({
  selector: 'app-donote',
  templateUrl: './donote.component.html',
  styleUrls: ['./donote.component.scss']
})
export class DonoteComponent implements OnInit,OnChanges{
  
  @Input() deadline:Date;
  constructor(private _snackBar: MatSnackBar) { }
  todos=TODOS;
  done = [];
  //dopilit' proverku na izmeneniya 
  ngOnChanges(changes:SimpleChanges){
    console.log(changes);
  }
  ngOnInit() {
    //parsing deadlines
    setInterval(() => {
      this.pareseDeadLine(this.todos); 
      }, 1000);
    //get items from local storage
    this.todos=JSON.parse(localStorage.getItem('todos'));
    this.done=JSON.parse(localStorage.getItem('done'));
    if(this.done==null){
      this.done=[];
    }
    if(this.todos==null){
      this.todos=[];
    }
  } 
  //parsing deadlines and alert user when it will be
  pareseDeadLine(array){
    for(let i in array){
      if(array[i].deadline!=undefined){
        var deadline=formatDate(array[i].deadline,"medium","en-US");
        var temp=formatDate(Date.now(),"medium","en-Us");
        if(deadline.toString()==temp.toString()){
        this._snackBar.open(array[i].text+' deadline passed', '', {
          duration: 10000,
        });
        playAuido();
        }

      }
    }
  this.findCollizion(array);
  }
  //select todo on click
  todo=new Todo('', undefined,undefined,undefined,false);
  selectedTodo:Todo;
  onSelect(todo:Todo){
    this.selectedTodo=todo;
  }
  //add new todo to list
  onSubmit(){
    if(this.todo.text.length!=0){
      if(this.validate()==true){
        var tempTodo:Todo={
          id:this.todo.id,text:this.todo.text,date:Date.now(),deadline:this.todo.deadline,done:false
        }
        tempTodo.id=this.todos.length+1;
        this.todos.push(tempTodo);
        let temp=JSON.stringify(this.todos);
        localStorage.setItem('todos',temp);
        this.todo.text=undefined; 
        this.todo.id=undefined; 
      }else{
        var text=this.todo.text;
      }
    }
   }
  //validate todo
  validate(){
    if(this.todo.text.length<60){
      return true;
    }else{
      return false;
    }
  }
  //find colizion in deadlienes
  findCollizion(array){
    for(let i in array){
      for(let j in array){
        if(i!=j){
          if(array[i].deadline!=undefined&&array[j].deadline!=undefined){
            var deadline1=formatDate(array[i].deadline,"medium","en-US");
            var deadline2=formatDate(array[j].deadline,"medium","en-US");
            if(deadline1.toString()==deadline2.toString()){
              console.log(array[i].text);
              console.log(array[j].text);
              return ;
            }
          }
        }
      }
    }
  }
  //drag and drop function
  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
          event.container.data,
          event.previousIndex,
          event.currentIndex);
    let foo=JSON.stringify(this.todos);
    localStorage.setItem('todos',foo);
    let temp=JSON.stringify(this.done);
    localStorage.setItem('done',temp);
    }
  }
  //delete todo
  delete(todo:Todo){
    for(let i=0;i<this.todos.length;i++){
      if(this.todos[i].id===this.selectedTodo.id){
        this.todos.splice(i,1);
        let temp=JSON.stringify(this.todos);
        localStorage.setItem('todos',temp);
      }
    }
  }
}

import { Component,OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public submitted: boolean;
  roomsearch : FormGroup;
  rooms : Room[];

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    this.rooms = ROOMS;
  }

  onSubmit({value, valid}: {value: Roomsearch, valid: boolean}) {
    console.log(value);
  }
}

export interface Roomsearch {
  checkin: string;
  checkout: string;
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
}

var ROOMS: Room[] = [
  {
    id: "123546",
    roomNumber: "409",
    price: "200",
    links: ""
  },
  {
    id: "123546",
    roomNumber: "410",
    price: "150",
    links: ""
  },
  {
    id: "123544",
    roomNumber: "411",
    price: "205",
    links: ""
  }
];

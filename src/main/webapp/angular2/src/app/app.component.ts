import { Component,OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private http:HttpClient){}

  private baseUrl:string = 'http://localhost:8080';
  public submitted: boolean;
  roomsearch : FormGroup;
  rooms : Room[];

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });
  }

  onSubmit({value, valid}: {value: Roomsearch, valid: boolean}) {
    this.http.get(this.baseUrl + '/room/reservation/v1?checkin=2017-03-18&checkout=2017-03-25')
      .subscribe((data) =>{
        this.rooms = data['content'];
      });
  }

  reserveRoom(value:String) {
    console.log("Room id for reservation: " + value);
  }

  /*getAll():Observable<Room[]> {
    return this.http.get(this.baseUrl + '/room/reservation/v1?checkin=2017-03-18&checkout=2017-03-25')
      .pipe(map(this.mapRoom));
  }

  mapRoom(response:Response):Room[] {
    return response.json().content;
  }*/
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

/*
var ROOMS: Room[] = [
  {
    id: "123546",
    roomNumber: "409",
    price: "200",
    links: ""
  },
  {
    id: "123540",
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
*/

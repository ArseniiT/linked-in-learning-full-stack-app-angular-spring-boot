import { Component,OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {RequestOptions} from "@angular/http";

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
  request:ReserveRoomRequest;
  currentCheckInVal:string;
  currentCheckOutVal:string;

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    roomsearchValueChanges$.subscribe( valChange => {
      this.currentCheckInVal = valChange.checkin;
      this.currentCheckOutVal = valChange.checkout;
    })
  }

  onSubmit({value, valid}: {value: Roomsearch, valid: boolean}) {
    this.http.get(this.baseUrl + '/room/reservation/v1?checkin=2017-03-18&checkout=2017-03-25')
      .subscribe((data) =>{
        this.rooms = data['content'];
      },
        err => console.log(err)
      );
  }

  reserveRoom(value:string) {
    this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

    this.createReservation(this.request);
  }

  createReservation(body: ReserveRoomRequest) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    let bodyString = JSON.stringify(body);
    this.http.post(this.baseUrl + '/room/reservation/v1', body, httpOptions)
      .subscribe(res => console.log(res))


    /*let bodyString = JSON.stringify(body);
    let headers = new Headers({'Content-Type': 'application/json'});
    let option = new RequestOptions({headers: headers})

    this.http.post(this.baseUrl + '/room/reservation/v1', body, headers);*/
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

export class ReserveRoomRequest {
  roomId:string;
  checkin:string;
  checkout:string;

  constructor(
    roomId:string,
    checkin:string,
    checkout:string
  ) {
    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
  
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

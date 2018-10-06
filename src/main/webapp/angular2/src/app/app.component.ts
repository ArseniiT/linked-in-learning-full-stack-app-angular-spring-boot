import { Component,OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

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
    this.http.get(this.baseUrl + '/room/reservation/v1?checkin=' + this.currentCheckInVal + '&checkout=' + this.currentCheckOutVal)
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

  createReservation(body: Object/*ReserveRoomRequest*/) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    let bodyString = JSON.stringify(body);
    this.http.post(this.baseUrl + '/room/reservation/v1', body, httpOptions)
      .subscribe(res => console.log(res))
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

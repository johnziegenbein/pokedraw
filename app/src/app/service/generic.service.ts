import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GenericService {

  private SERVER_URL = 'http://localhost:8080';
  private GENERIC_PATH = '/generic';

  constructor(private http: HttpClient) { }

  public getGenericInfo() {
    return this.http.get(this.SERVER_URL + this.GENERIC_PATH);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GenericService {

  private SERVER_URL = 'http://localhost:8080';
  private GENERIC_PATH = '/pokemon';

  constructor(private http: HttpClient) { }

  public getAllPokemon() {
    return this.http.get(this.SERVER_URL + this.GENERIC_PATH);
  }
}

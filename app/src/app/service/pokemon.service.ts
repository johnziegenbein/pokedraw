import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscriber} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  private SERVER_URL = 'http://localhost:8080';
  private POKEMON_PATH = '/pokemon';

  constructor(private http: HttpClient) { }

  public getAllPokemon() {
    return this.http.get(this.SERVER_URL + this.POKEMON_PATH);
  }

  public getByFilter(filter: string) {
    return new Observable();
  }


  public replacePokemon(id: string, name: string, drawn: boolean) {
    const body = {
      id,
      name,
      drawn
    };
    return this.http.put(this.SERVER_URL + this.POKEMON_PATH, body);
  }
}

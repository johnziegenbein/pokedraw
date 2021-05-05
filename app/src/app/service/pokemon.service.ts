import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscriber} from "rxjs";
import * as url from "url";

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

  public findByFilter(filter: string) {
    return this.http.get(this.SERVER_URL + '/pokemon/findByFilter', {
      params: {
        filter
      }
    });
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

import { Component, OnInit } from '@angular/core';
import {PokemonService} from '../service/pokemon.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  pokemonData;

  constructor(private pokemonService: PokemonService) { }

  ngOnInit() {
    this.pokemonService.getAllPokemon().subscribe((data: any[]) => {
      console.log(data);
      this.pokemonData = data;
    });
  }

}

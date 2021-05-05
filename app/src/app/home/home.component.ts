import { Component, OnInit } from '@angular/core';
import {PokemonService} from '../service/pokemon.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  pokemonData;

  filter: string;

  constructor(private pokemonService: PokemonService) { }

  ngOnInit() {
    this.pokemonService.getAllPokemon().subscribe((data: any[]) => {
      console.log(data);
      this.pokemonData = data;
    });
  }

  onFilterChange(event: any) {
    this.filter = event;
    if (this.filter !== '') {
      this.pokemonService.findByFilter(this.filter).subscribe((data: any[]) => {
        console.log(data);
        this.pokemonData = data;
      });
    }
  }
}

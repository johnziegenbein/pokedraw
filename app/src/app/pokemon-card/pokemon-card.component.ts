import {Component, Input, OnInit} from '@angular/core';
import {PokemonService} from "../service/pokemon.service";

@Component({
  selector: 'app-pokemon-card',
  templateUrl: './pokemon-card.component.html',
  styleUrls: ['./pokemon-card.component.scss']
})
export class PokemonCardComponent implements OnInit {
  @Input() name: string;
  @Input() id: string;
  @Input() drawn: boolean;

  constructor(private pokemonService: PokemonService) { }

  ngOnInit(): void {
  }

  onValChange() {
    this.drawn = !this.drawn;
    this.pokemonService.replacePokemon(this.id, this.name, this.drawn)
      .subscribe({
        next: data => {
          console.log(data);
          this.drawn = data['drawn'];
        },
        error: error => {
          console.error('There was an error!', error);
        }
      });
  }
}

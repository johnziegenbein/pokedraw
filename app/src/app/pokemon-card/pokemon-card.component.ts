import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-pokemon-card',
  templateUrl: './pokemon-card.component.html',
  styleUrls: ['./pokemon-card.component.scss']
})
export class PokemonCardComponent implements OnInit {
  @Input() name: string;
  @Input() id: string;
  @Input() drawn: boolean;

  constructor() { }

  ngOnInit(): void {
  }

}

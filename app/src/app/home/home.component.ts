import { Component, OnInit } from '@angular/core';
import {GenericService} from '../service/generic.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  genericInfo = '';

  constructor(private genericService: GenericService) { }

  ngOnInit() {
    this.genericService.getGenericInfo().subscribe((data: any[]) => {
      console.log(data);
      this.genericInfo = data['content'];
    });
  }

}

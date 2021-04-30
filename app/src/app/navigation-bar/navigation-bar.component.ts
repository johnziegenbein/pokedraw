import {Component, OnInit} from '@angular/core';
import {MODE, ThemeService} from '../theme.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {
  isDarkMode: boolean;

  constructor(private themeService: ThemeService) {
    this.themeService.initTheme();
    this.isDarkMode = this.themeService.isDarMode();
  }

  ngOnInit() {
  }

  toggleDarkMode() {
    this.isDarkMode = this.themeService.isDarMode();

    this.isDarkMode ? this.themeService.update(MODE.LIGHT_MODE) :
                      this.themeService.update(MODE.DARK_MODE);
  }
}

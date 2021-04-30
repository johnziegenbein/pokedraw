import {Injectable, Renderer2, RendererFactory2} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private renderer: Renderer2;
  private colorTheme: string;

  constructor(rendererFactory: RendererFactory2) {
    this.renderer = rendererFactory.createRenderer(null, null);
  }

  initTheme() {
    this.getColorTheme();
    this.renderer.addClass(document.body, this.colorTheme);
  }

  update(theme: MODE) {
    this.setColorTheme(theme);

    const previousColorTheme = (
      theme === MODE.DARK_MODE ? MODE.LIGHT_MODE : MODE.DARK_MODE);
    this.renderer.removeClass(document.body, previousColorTheme);
    this.renderer.addClass(document.body, theme);
  }

  isDarMode() {
    return this.colorTheme === MODE.DARK_MODE;
  }

  private setColorTheme(theme) {
    this.colorTheme = theme;
    localStorage.setItem('user-theme', theme);
  }

  private getColorTheme() {
    if (localStorage.getItem('user-theme')) {
      this.colorTheme = localStorage.getItem('user-theme');
    }
    else {
      this.colorTheme = MODE.LIGHT_MODE;
    }
  }
}

export enum MODE {
  DARK_MODE = 'dark-mode',
  LIGHT_MODE = 'light-mode'
}

import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NavigationComponent } from './navigation/navigation.component';
import { TvShowComponent } from './tv-show/tv-show.component';


@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    RouterOutlet,
    HomeComponent,
    NavigationComponent,
    TvShowComponent,
    RouterLink,
  ],
})
export class AppComponent {
  title = 'ngTvTracker';
}

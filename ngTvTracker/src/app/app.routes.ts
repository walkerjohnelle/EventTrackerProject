import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { TvShowComponent } from './tv-show/tv-show.component';
import { RatingComponent } from './rating/rating.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'shows', component: TvShowComponent},
  { path: 'ratings', component: RatingComponent},
  { path: '**', component: NotFoundComponent },
];

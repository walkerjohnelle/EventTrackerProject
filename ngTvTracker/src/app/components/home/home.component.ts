import { TvShowService } from './../../services/tv-show.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TvShow } from '../../models/tv-show';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  shows: TvShow[] = [];
  selected: TvShow | null = null;
  editShow: TvShow | null = null;
  newShow: TvShow = new TvShow();

  constructor(
    private showService: TvShowService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.loadTvShows();
    this.activatedRoute.paramMap.subscribe({
      next: (params) => {
        let showIdStr = params.get('showId');
        if (showIdStr) {
          let showId = parseInt(showIdStr);
          if (isNaN(showId)) {
            this.router.navigateByUrl('invalidShowId');
          } else {
            this.getTvShow(showId);
          }
        }
      },
    });
  }
  getTvShow(showId: number) {
    this.showService.show(showId).subscribe({
      next: (show) => {
        this.selected = show;
      },
      error: (oops) => {
        console.error('TodoListComponent.getTodo: error getting todo.');
        console.error(oops);
        this.router.navigateByUrl('todoNotFound');
      },
    });
  }

  loadTvShows() {
    this.showService.index().subscribe({
      next: (showList) => {
        this.shows = showList;
      },
      error: (oops) => {
        console.error(
          'TvShowListComponent.loadTvShows(): error loading TV Shows:'
        );
        console.error(oops);
      },
    });
  }
}

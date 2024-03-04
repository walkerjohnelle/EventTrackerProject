import { Component, OnInit } from '@angular/core';
import { TvShowService } from '../services/tv-show.service';
import { TvShow } from '../models/tv-show';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tv-show',
  standalone: true,
  imports: [CommonModule, NgbModule, FormsModule],
  templateUrl: './tv-show.component.html',
  styleUrls: ['./tv-show.component.css'],
})
export class TvShowComponent implements OnInit {
  tvShows: TvShow[] = [];
  selectedShow: TvShow = new TvShow();
  newShow: TvShow = new TvShow();
  userId: number = 0;
  isEditMode: boolean = false;

  constructor(
    private tvShowService: TvShowService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadTvShows();
  }

  loadTvShows(): void {
    this.tvShowService.index().subscribe({
      next: (shows) => (this.tvShows = shows),
      error: (error) => console.error('Error loading TV shows:', error),
    });
  }

  selectShow(show: TvShow): void {
    this.selectedShow = { ...show };
    this.isEditMode = true;
  }

  handleSubmit(): void {
    if (this.isEditMode) {
      this.updateExistingShow(this.selectedShow);
    } else {
      this.addNewShow(this.newShow);
    }
  }
  addNewShowButtonClicked(): void {
    this.selectedShow = new TvShow(); // Reset to a new object
  }

  updateExistingShow(show: TvShow): void {
    this.tvShowService.update(show.id, show).subscribe({
      next: () => {
        this.loadTvShows(); // Refresh list
        this.selectedShow = new TvShow(); // Reset form
      },
      error: (error) => console.error('Error updating TV show:', error),
    });
  }

  addNewShow(show: TvShow): void {
    this.tvShowService.create(show).subscribe({
      next: (newShow) => {
        this.tvShows.push(newShow);
        console.log('Adding new show:', show);

        this.loadTvShows();
        this.newShow = new TvShow(); // Reset newShow object
      },
      error: (error) => console.error('Error creating TV show:', error),
    });
  }

  deleteShow(showId: number): void {
    this.tvShowService.destroy(showId).subscribe({
      next: () => {
        this.tvShows = this.tvShows.filter((show) => show.id !== showId);
        if (this.selectedShow.id === showId) {
          this.loadTvShows();
        }
      },
      error: (error) => console.error('Error deleting TV show:', error),
    });
  }

  clearSelection(): void {
    this.loadTvShows();
  }
}

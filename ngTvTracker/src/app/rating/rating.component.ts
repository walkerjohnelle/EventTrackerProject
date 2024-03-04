import { Component, OnInit } from '@angular/core';
import { RatingService } from '../services/rating.service';
import { Rating } from '../models/rating';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TvShow } from '../models/tv-show';
import { TvShowService } from '../services/tv-show.service'; // Assume you have this service

@Component({
  selector: 'app-rating',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css'],
})
export class RatingComponent implements OnInit {
  tvShows: TvShow[] = [];
  ratings: Rating[] = [];
  selectedRating: Rating | null = null;
  selectedTvShowId: any;

  constructor(
    private ratingService: RatingService,
    private tvShowService: TvShowService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadRatings();
    this.loadTvShows();
  }

  loadRatings(): void {
    this.ratingService.index().subscribe({
      next: (ratings) => (this.ratings = ratings),
      error: (error) => console.error('Error retrieving ratings:', error),
    });
  }

  loadTvShows(): void {
    this.tvShowService.index().subscribe({
      next: (tvShows) => (this.tvShows = tvShows),
      error: (error) => console.error('Error retrieving TV shows:', error),
    });
  }

  selectRating(rating: Rating): void {
    this.selectedRating = { ...rating }; // Clone the rating for editing
  }

  saveRating(rating: Rating): void {
    if (this.selectedTvShowId) {
      rating.tvShow = { id: this.selectedTvShowId };
    }
    if (rating.id) {
      this.updateRating(rating);
    } else {
      this.createRating(rating);
    }
  }

  createRating(rating: Rating): void {
    this.ratingService.create(rating).subscribe({
      next: (newRating) => {
        this.ratings.push(newRating);
        this.clearSelection(); // Reset selection
        console.log('Rating created:', newRating);
      },
      error: (error) => console.error('Error creating rating:', error),
    });
  }

  updateRating(rating: Rating): void {
    this.ratingService.update(rating.id, rating).subscribe({
      next: () => {
        const index = this.ratings.findIndex((r) => r.id === rating.id);
        this.ratings[index] = rating;
        this.clearSelection(); // Reset selection
        console.log('Rating updated:', rating);
      },
      error: (error) => console.error('Error updating rating:', error),
    });
  }

  deleteRating(ratingId: number): void {
    this.ratingService.destroy(ratingId).subscribe({
      next: () => {
        this.ratings = this.ratings.filter((r) => r.id !== ratingId);
        console.log('Rating deleted:', ratingId);
      },
      error: (error) => console.error('Error deleting rating:', error),
    });
  }

  clearSelection(): void {
    this.selectedRating = null;
  }

  addNewRatingButtonClicked(): void {
    this.selectedRating = new Rating(); // Prepare a new rating
  }
}

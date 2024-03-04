export class Rating {
  id: number;
  rating: number;
  review: string;
  tvShow: { id: number; };

  constructor(id: number = 0, rating: number = 0, review: string = '', tvShowId: number = 0) {
    this.id = id;
    this.rating = rating;
    this.review = review;
    this.tvShow = {id: tvShowId};

  }
}

export class Rating {
  id: number;
  rating: number;
  review: string;

  constructor(id: number = 0, rating: number = 0, review: string = '') {
    this.id = id;
    this.rating = rating;
    this.review = review;
  }
}

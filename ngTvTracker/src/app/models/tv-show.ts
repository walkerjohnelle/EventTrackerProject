import { Rating } from "./rating";

export class TvShow {
  id: number;
  title: string;
  genre: string;
  description: string;
  releaseYear: number;
  seasons: number;
  totalEpisodes: number;
  streamingPlatform: string;
  active: boolean;
  ratings: Rating [];

  constructor(id: number = 0, title: string = '', description: string = '', releaseYear: number = 0,
  genre: string = '', seasons: number = 0, totalEpisodes: number = 0, streamingPlatform: string = '',
  active: boolean = false) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.description = description;
    this.releaseYear = releaseYear;
    this.seasons = seasons;
    this.totalEpisodes = totalEpisodes;
    this.streamingPlatform = streamingPlatform;
    this.active = active;
    this.ratings = [];
  }
}

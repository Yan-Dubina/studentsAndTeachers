import {Comment} from './Comment';
import {Artist} from "./Artist";
import {Type} from "./Type";

export interface Product {
  id: number;
  type: Type;
  image?: File;
  cost: number;
  description: string;
  comments?: Comment[];
  artist: Artist;
}

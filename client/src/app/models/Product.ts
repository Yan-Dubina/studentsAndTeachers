import {Comment} from './Comment';
import {Singer} from "./Singer";

export interface Product {
  id?: number;
  type: string;
  image?: File;
  cost: number;
  description: string;
  comments?: Comment[];
  singer: Singer;
}

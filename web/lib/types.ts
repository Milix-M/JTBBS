export interface CommentType {
  id?: number;
  handleName?: string;
  commentText: string;
  createdAt: string;
}

export interface BoardType {
  id?: number;
  name: string;
  comments?: CommentType[]
  createdAt: string;
}

export interface ErrorResponce {
  detail: string;
}

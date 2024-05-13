export interface CommentType {
  id?: number;
  handle_name?: string;
  comment_text: string;
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

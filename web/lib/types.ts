export interface CommentType {
  id?: number;
  handle_name?: string;
  comment_order_number?: number;
  comment_text: string;
}

export interface BoardType {
  id?: number;
  board_name: string;
}

export interface ErrorResponce {
  detail: string;
}

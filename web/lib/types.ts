export interface HttpResponseType {
  httpStatus: string;
  message?: string;
}
export interface CommentResType extends HttpResponseType {
  responseData: CommentType[];
}

export interface BoardResType extends HttpResponseType {
  responseData: BoardType[];
}

export interface CommentType {
  id?: number;
  handle_name?: string;
  comment_order_number?: number;
  comment_text: string;
}

export interface BoardType {
  id?: number;
  name: string;
}

export interface ErrorResponce {
  detail: string;
}

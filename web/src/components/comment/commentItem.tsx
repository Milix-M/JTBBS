import { FC } from "react";
import { CommentType } from "../../../lib/types";

const CommentItem: FC<CommentType> = (props) => {
  return (
    <div className="p-2 m-2 border rounded-2xl">
      <div className="flex">
        <span className="mr-2 text-md font-bold">{props.handleName}</span>
        <span className="text-xs content-center">
          投稿日時:{props.createdAt}
        </span>
      </div>
      <p>{props.commentText}</p>
    </div>
  );
};

export default CommentItem;

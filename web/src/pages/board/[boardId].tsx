import CommentItem from "@/components/comment/commentItem";
import { useBoardInfo } from "@/hooks/fetchboard";
import { useRouter } from "next/router";

const Board = () => {
  const router = useRouter();
  const { boardData, isLoading, error } = useBoardInfo(
    router.query.boardId as string
  );

  return (
    <div>
      <div className="flex justify-center">
        <div>
          <h1 className="text-2xl">{boardData?.name}</h1>
        </div>
      </div>
      <div className="flex justify-center">
        <div className="max-w-xl w-full">
          {boardData?.comments !== undefined &&
            boardData?.comments?.map((comment, index) => (
              <CommentItem
                handleName={comment.handleName}
                commentText={comment.commentText}
                createdAt={comment.createdAt}
                key={index}
              />
            ))}
        </div>
      </div>
    </div>
  );
};

export default Board;

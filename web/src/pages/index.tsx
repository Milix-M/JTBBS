import BoardSelectBtn from "@/components/button/boardSelectBtn";
import getLayout from "@/components/layouts/non_headder";
import { useBoardList } from "@/hooks/fetchboard";

const Index = () => {
  const { boards, isLoading, error } = useBoardList();

  return (
    <div>
      <div>
        <h1 className="text-4xl italic font-bold text-center tracking-widest">
          JTBBS
        </h1>
        <p className="text-center">Java TypeScript BBS</p>
      </div>

      <div className="mt-5">
        <h4 className="text-center font-bold">掲示板一覧</h4>
        <div className="flex justify-center p-2">
          <div className="flex justify-center">
            {boards !== undefined &&
              boards.map((board, index) => (
                <BoardSelectBtn
                  boardName={board.name}
                  url={`/board/${board.id}`}
                  key={index}
                />
              ))}
          </div>
        </div>
      </div>
    </div>
  );
};

Index.getLayout = getLayout;

export default Index;

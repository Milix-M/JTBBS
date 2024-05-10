import BoardSelectBtn from "@/components/button/boardSelectBtn";
import getLayout from "@/components/layouts/non_headder";

const Index = () => {
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
          <div>
            <BoardSelectBtn url="/java" boardName="java" />
          </div>
        </div>
      </div>
    </div>
  );
};

Index.getLayout = getLayout;

export default Index;
